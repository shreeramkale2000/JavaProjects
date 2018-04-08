package com.ritvik.dao;

import com.ritvik.spring.Employee;

public interface JdbcDaoEx {
	
	public int postData(int id, String name);
	
	public Employee getData(int id);

}
