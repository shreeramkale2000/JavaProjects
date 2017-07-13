package org.ritvik;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class TestDao {
	
	private Logger logger = Logger.getLogger(TestDao.class);
	
	public String getData () throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String date = null;
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("VALUES CURRENT_TIMESTAMP");
			while (rs.next()){
				date = rs.getString(1);
			}
			logger.info("Selected Data " + date);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		} finally {
			if (rs != null){
				rs.close();
			}
			
			if (stmt != null){
				stmt.close();
			}
			
			if (conn != null){
				conn.close();
			}
		}
		
		return date;
	}
	
	public Connection getConnection() throws NamingException, SQLException {
		Connection conn = null;
        InitialContext ic = new InitialContext();

        DataSource ds = (DataSource) ic.lookup("java:/DerbyEmbedDS");
        conn = ds.getConnection();
        
        return conn;
    }
}
