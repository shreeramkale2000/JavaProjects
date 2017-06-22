package com.ritvik.spring;

import com.ritvik.dao.JdbcDaoImpl;
import com.ritvik.dao.JdbcDaoSupportImpl;
import com.ritvik.dao.NamedJdbcDaoImpl;

public class BusinessLogic {
	
	public void start() {
		JdbcDaoImpl jdbcDaoImpl = App.context.getBean("jdbcDaoImpl", JdbcDaoImpl.class);
		JdbcDaoSupportImpl jdbcDaoSupportImpl = App.context.getBean("jdbcDaoSupportImpl", JdbcDaoSupportImpl.class);
		NamedJdbcDaoImpl namedJdbcDaoImpl = App.context.getBean("namedJdbcDaoImpl", NamedJdbcDaoImpl.class);
		
		jdbcDaoImpl.getDataCount();
		
		namedJdbcDaoImpl.deleteData(999999999);
		
		jdbcDaoSupportImpl.postData(999999999, "051DISCREQ999999999.xml");
		jdbcDaoSupportImpl.getData(999999999).getName();
	}

}
