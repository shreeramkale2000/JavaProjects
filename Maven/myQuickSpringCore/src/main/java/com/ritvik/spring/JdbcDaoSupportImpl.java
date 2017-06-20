package com.ritvik.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcDaoSupportImpl extends JdbcDaoSupport{
	
	@Value("${data.select.msg}")
	private String data_select_msg;
	
	@Value("${data.insert.msg}")
	private String data_insert_msg;
	
	private Logger logger = Logger.getLogger(JdbcDaoSupportImpl.class);
	
	public int postData(int id, String name){
		int size = getJdbcTemplate().update("INSERT INTO EMP VALUES (?, ?)", new Object[]{id, name});
		logger.info(data_insert_msg + size);
		return size;
	}
	
	public Employee getData(int id){
		Employee emp = getJdbcTemplate().queryForObject("SELECT * FROM EMP WHERE ID = ?", new Object[]{id} , new EmpRowMapper()); 
		logger.info(data_select_msg + emp.getName());
		return emp;
	}
	
	public static final class EmpRowMapper implements RowMapper<Employee> {
		
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee emp = new Employee();
			emp.setId(rs.getInt("ID"));
			emp.setName(rs.getString("NAME"));
			return emp;
		}
		
	}
}
