package jain.ritvik.utils.connectors;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import jain.ritvik.utils.common.Errors;
import jain.ritvik.utils.exceptions.JNDINotSetException;

public class DBConnectionManager {

	public static DBConnectionManager connectionManager = null;

	private DataSource ds = null;
	private InitialContext ctxt = null;
	private String sJNDIName;

	private String ClassName = "";
	private String URLString = "";
	private String ServerIP = "";
	private String Service = "";
	private String Port = "";
	private String Username = "";
	private String Password = "";
	private boolean DefaultAutoCommit = false;
	
	private int InitialSize = 1;
	private int MaxActive = 1;
	private int MaxIdle = 1;
	private int MaxWait=60;
	private int MinIdle=1;
	private boolean TestWhileIdle = true;
	
	private DBConnectionManager() {
		// createConnectionPool();
	}

	public static DBConnectionManager getInstance() {

		if (connectionManager == null) {
			connectionManager = new DBConnectionManager();
		}
		return connectionManager;
	}

	private void createConnectionPool() throws JNDINotSetException {

		if (sJNDIName != null && sJNDIName.length() > 0) {

			try {
				ctxt = new InitialContext();
				ds = (DataSource) ctxt.lookup(sJNDIName);
			} catch (NamingException ex) {
				throw new JNDINotSetException(Errors.ISD_00003);
			}
		} else {
			
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(ClassName);
			bds.setUrl(getOracleURL());
			bds.setDefaultAutoCommit(false);
			bds.setUsername(Username);
			bds.setPassword(Password);
			bds.setInitialSize(InitialSize);
			bds.setMaxIdle(MaxIdle);
			bds.setMinIdle(MinIdle);
			bds.setTestWhileIdle(TestWhileIdle);
			
			ds = bds;
			bds = null;
			
		}
	}

	public Connection getConnection() throws SQLException, JNDINotSetException {

		Connection conn = null;

		if (ds == null) {
			createConnectionPool();
			conn = ds.getConnection();
		} else {
			conn = ds.getConnection();
		}

		return conn;
	}

	public void setJNDIName(String JNDIName) {
		sJNDIName = JNDIName;
	}

	private String getOracleURL(){
		return URLString + ServerIP + ":" + Port + ":" + Service;
	}
	
	public void setClassName(String className) {
		ClassName = className;
	}

	public void setURLString(String string) {
		URLString = string;
	}

	public void setServerIP(String serverIP) {
		ServerIP = serverIP;
	}

	public void setService(String service) {
		Service = service;
	}

	public void setPort(String port) {
		Port = port;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getClassName() {
		return ClassName;
	}

	public String getURLString() {
		return URLString;
	}

	public String getServerIP() {
		return ServerIP;
	}

	public String getService() {
		return Service;
	}

	public String getPort() {
		return Port;
	}

	public String getUsername() {
		return Username;
	}

	public String getPassword() {
		return Password;
	}

	/*public boolean getConnectionStatus() {
		return checkConnection;
	}*/

	/**
	 * @return the initialSize
	 */
	public int getInitialSize() {
		return InitialSize;
	}

	/**
	 * @param initialSize
	 *            the initialSize to set
	 */
	public void setInitialSize(int initialSize) {
		InitialSize = initialSize;
	}

	/**
	 * @return the maxActive
	 */
	public int getMaxActive() {
		return MaxActive;
	}

	/**
	 * @param maxActive
	 *            the maxActive to set
	 */
	public void setMaxActive(int maxActive) {
		MaxActive = maxActive;
	}

	/**
	 * @return the maxIdle
	 */
	public int getMaxIdle() {
		return MaxIdle;
	}

	/**
	 * @param maxIdle
	 *            the maxIdle to set
	 */
	public void setMaxIdle(int maxIdle) {
		MaxIdle = maxIdle;
	}

	/**
	 * @return the maxWait
	 */
	public int getMaxWait() {
		return MaxWait;
	}

	/**
	 * @param maxWait
	 *            the maxWait to set
	 */
	public void setMaxWait(int maxWait) {
		MaxWait = maxWait;
	}

	/**
	 * @return the minIdle
	 */
	public int getMinIdle() {
		return MinIdle;
	}

	/**
	 * @param minIdle
	 *            the minIdle to set
	 */
	public void setMinIdle(int minIdle) {
		MinIdle = minIdle;
	}

	/**
	 * @return the testWhileIdle
	 */
	public boolean isTestWhileIdle() {
		return TestWhileIdle;
	}

	/**
	 * @param testWhileIdle
	 *            the testWhileIdle to set
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		TestWhileIdle = testWhileIdle;
	}

	/**
	 * @return the defaultAutoCommit
	 */
	public boolean isDefaultAutoCommit() {
		return DefaultAutoCommit;
	}

	/**
	 * @param defaultAutoCommit
	 *            the defaultAutoCommit to set
	 */
	public void setDefaultAutoCommit(boolean defaultAutoCommit) {
		DefaultAutoCommit = defaultAutoCommit;
	}

}
