package com.ritvik.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ritvik.dao.JdbcDaoSuportImpl.EmpRowMapper;
import com.ritvik.spring.Employee;

@Repository("namedJdbcDaoImpl")
public class NamedJdbcDaoImpl implements NamedJdbcDao {
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Value("${data.delete.msg}")
	private String data_delete_msg;
	
	private Logger logger = Logger.getLogger("org.ritvik.processLog");
 
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }
    
    @Override
	public int getDataCount(){
		int size = getNamedParameterJdbcTemplate().query("SELECT * FROM EMP", new EmpRowMapper()).size();
		logger.debug("Data Size : " + size);
		return size;
	}
    
    @Override
    public int deleteData(int id){
    	//int size = getNamedParameterJdbcTemplate().update("DELETE FROM EMP WHERE ID = :id", new MapSqlParameterSource().addValue("id", id));
    	Employee emp = new Employee();
    	emp.setId(id);
    	int size = getNamedParameterJdbcTemplate().update("DELETE FROM EMP WHERE ID = :id", new BeanPropertySqlParameterSource(emp));
		logger.debug(data_delete_msg + size);
		
		String date = getNamedParameterJdbcTemplate().query("VALUES CURRENT_TIMESTAMP", new DateRowMapper()).get(0);
		logger.debug("Current Date - " + date);
		
		return size;
	}
    
    public static final class DateRowMapper implements RowMapper<String> {

		@Override
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString(1);
		}

	}
	
}