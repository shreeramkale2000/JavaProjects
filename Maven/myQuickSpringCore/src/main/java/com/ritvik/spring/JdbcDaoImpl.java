package com.ritvik.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ritvik.spring.JdbcDaoSupportImpl.EmpRowMapper;

@Repository("jdbcDaoImpl")
public class JdbcDaoImpl {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(JdbcDaoImpl.class);
	
	public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
	
	public int getDataCount(){
		int size = getJdbcTemplate().query("SELECT * FROM EMP", new EmpRowMapper()).size();
		logger.info("Data Size : " + size);
		return size;
	}
}
