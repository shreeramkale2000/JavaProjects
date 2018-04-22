package com.ritvik.dao;

import java.io.StringWriter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateDaoImpl {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	protected StringWriter stackTraceWriter;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}