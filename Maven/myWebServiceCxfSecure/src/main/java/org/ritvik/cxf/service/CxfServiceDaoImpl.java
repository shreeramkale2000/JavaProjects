package org.ritvik.cxf.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("cxfServiceDaoImpl")
public class CxfServiceDaoImpl implements CxfServiceDao{
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private StringWriter stackTraceWriter;

	private Logger logger = Logger.getLogger("org.ritvik.processLog");

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

	public String getDate(){
		String date = "";
		try {
			date = getNamedParameterJdbcTemplate().query("VALUES CURRENT_TIMESTAMP", new DateRowMapper()).get(0);
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
