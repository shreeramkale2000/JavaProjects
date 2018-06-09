package com.ritvik.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import com.ritvik.dao.JdbcDao;
import com.ritvik.dao.JdbcDaoSuport;
import com.ritvik.dao.NamedJdbcDao;

@Repository("businessLogic")
public class BusinessLogic {
	
	//@Autowired
	//private JdbcDao jdbcDaoImpl;
	
	@Autowired
	private JdbcDaoSuport jdbcDaoSuportImpl;
	
	@Autowired
	private NamedJdbcDao namedJdbcDaoImpl;
	
	public void start() {
		//jdbcDaoImpl.createTables();
		
		namedJdbcDaoImpl.getDataCount();
		namedJdbcDaoImpl.deleteData(999999999);
		
		jdbcDaoSuportImpl.postData(999999999, "051DISCREQ999999999.xml");
		jdbcDaoSuportImpl.getData(999999999).getName();
	}

}
