package com.ritvik.spring;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.ritvik.utils.crypto.Transformer;
import org.ritvik.utils.exceptions.BlankStringException;

public class MyCustomBasicDataSource extends BasicDataSource {
	
	private Logger logger = Logger.getLogger(MyCustomBasicDataSource.class);
	
	@Autowired
	private StringWriter stackTraceWriter;
	
	@Autowired
	private Transformer transformer;

	public MyCustomBasicDataSource() {
		super();
	}

	public synchronized void setPassword(String password) {
		try {
			super.setPassword(transformer.decode(password));
		} catch (InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException | 
				BadPaddingException | IllegalBlockSizeException | BlankStringException e) {
			e.printStackTrace(new PrintWriter(stackTraceWriter));
			logger.fatal(stackTraceWriter.toString());
			stackTraceWriter.flush();
		}
	}

}