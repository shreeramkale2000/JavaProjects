package com.ritvik.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.ritvik.spring.Employee;

public class JdbcDaoSuportImpl extends JdbcDaoSupport implements JdbcDaoSuport{
	
	@Value("${data.select.msg}")
	private String data_select_msg;
	
	@Value("${data.insert.msg}")
	private String data_insert_msg;
	
	private Logger logger = Logger.getLogger("org.ritvik.processLog");
	
	@Override
	public int postData(int id, String name){
		int size = getJdbcTemplate().update("INSERT INTO EMP VALUES (?, ?)", new Object[]{id, name});
		logger.debug(data_insert_msg + size);
		return size;
	}
	
	@Override
	public Employee getData(int id){
		Employee emp = getJdbcTemplate().queryForObject("SELECT * FROM EMP WHERE ID = ?", new Object[]{id} , new EmpRowMapper()); 
		logger.debug(data_select_msg + emp.getName());
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
