package com.ritvik.spring;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("businessLogic")
public class BusinessLogic {
	
	private Logger logger = Logger.getLogger(BusinessLogic.class);
	
	public void start() {
		logger.info("Started");
	}

}