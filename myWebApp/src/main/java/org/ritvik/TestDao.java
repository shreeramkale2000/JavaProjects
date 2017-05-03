package org.ritvik;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TestDao {
	
	public String getData () throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String name = null;
		try {
			conn = getConnection();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select name from emp where id = 1");
			while (rs.next()){
				name = rs.getString("name");
			}
			
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
		
		return name;
	}
	
	public Connection getConnection() throws NamingException, SQLException {
		Connection conn = null;
        InitialContext ic = new InitialContext();

        DataSource ds = (DataSource) ic.lookup("java:/DerbyDS");
        conn = ds.getConnection();
        
        return conn;
    }
}
