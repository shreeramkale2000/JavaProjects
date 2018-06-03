package org.ritvik;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("testDaoSpringImpl")
public class TestDaoSpringImpl implements TestDaoSpring {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private StringWriter stackTraceWriter;
	
	@Value("${data.select.msg}")
	private String data_select_msg;

	private Logger logger = Logger.getLogger(TestDaoSpringImpl.class);

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	@Override
	public String getDate(){
		String date = "";
		try {
			date = getNamedParameterJdbcTemplate().query("VALUES CURRENT_TIMESTAMP", new DateRowMapper()).get(0);
			logger.debug(data_select_msg + date);
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(stackTraceWriter));
			logger.fatal(stackTraceWriter.toString());
			stackTraceWriter.flush();
		}
		
		return date;
	}

	public static final class DateRowMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString(1);
		}

	}

}
