package com.ritvik.dao;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdbcDaoImpl")
public class JdbcDaoImpl implements JdbcDao {
	
	@Autowired
	private StringWriter stackTraceWriter;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(JdbcDaoImpl.class);
	
	public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
	
	@Override
	public void createTables(){
		try {
			getJdbcTemplate().execute("CREATE TABLE EMP (ID int PRIMARY KEY NOT NULL, NAME varchar(50) NOT NULL)");
			logger.info("Created Table - EMP");
			getJdbcTemplate().execute("CREATE TABLE ADDR (ID int NOT NULL,ADDRESS varchar(50) NOT NULL)");
			getJdbcTemplate().execute("ALTER TABLE ADDR ADD CONSTRAINT ADDR_ID_REF FOREIGN KEY (ID) REFERENCES EMP(ID)");
			logger.info("Created Table - ADDR");
			getJdbcTemplate().execute("INSERT INTO EMP (ID,NAME) VALUES (1,'ritvik')");
			getJdbcTemplate().execute("INSERT INTO EMP (ID,NAME) VALUES (2,'pallav')");
			getJdbcTemplate().execute("INSERT INTO ADDR (ID,ADDRESS) VALUES (1,'ghansoli')");
			logger.info("Sample Data Added");
		} catch (DataAccessException e){
			e.printStackTrace(new PrintWriter(stackTraceWriter));
			logger.fatal(stackTraceWriter.toString());
			stackTraceWriter.flush();
		}
		
	}
}
