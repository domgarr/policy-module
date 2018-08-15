package com.policy.dao;

import java.sql.*;
/**
 * enum to manage connection with oracle database, and provide methods to
 * safely query from database and update it as well. I have provided an option
 * to get the Statement and work with it directly, though it is unsafe. I have
 * also provided a method to get a PreparedStatement so that can be used as needed.
 * 
 * Note that this is a singleton and only one instance of it will exist. 
 * 
 * Use the disconnect() method when connection needs to be closed.
 * 
 * To access the single instance of this enum use OracleConnection.INSTANCE
 * Example: ResultSet test = OracleConnection.Instance.executeQuery(query)
 * 
 * @author Nicholas, created 8/15
 *
 */
public enum OracleConnection {
	
	INSTANCE;
	
	private static Connection con = null;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static PreparedStatement pst = null;

	/**
	 * Static block initializes the connection to the database - only needs
	 * to be done once.
	 */
	static {
		
		try {
			//Driver informatoin
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//IP address, username and password
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uname = "system";
		String pwd = "tcs12345";
		
		
		try {
			//Initialize connection
			con = DriverManager.getConnection(url, uname, pwd);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			//Initialize statement
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Use this method to make changes to the database only. Will not work for 
	 * retrieving information from database.
	 * @param update The SQL command to be run
	 * @return
	 */
	public boolean executeUpdate(String update) {
		try {
			st.executeUpdate(update);
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Use this method to retrieve information from the database only. Cannot be
	 * used to change database in any way.
	 * @param query The SQL query to run
	 * @return the ResultSet containing information from database. This is null if
	 * there was an Exception.
	 */
	public ResultSet executeQuery(String query) {
		try {
			rs = st.executeQuery(query);
			return rs;
		}
		catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Get a PreparedStatement to work with. It is your responsibility to handle
	 * all potential SQLExceptions. 
	 * @param query The prepared query
	 * @return the PreparedStatement
	 */
	public PreparedStatement getPreparedStatement(String query) {
		try {
			pst = con.prepareStatement(query);
			return pst;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * If you wish to work with the Statement directly, this method allows you to 
	 * do so. However, the executeUpdate and executeQuery methods provided in this enum
	 * are recommended as they are safe.
	 * @return the Statement
	 */
	public Statement getStatement() {
		return st;
	}
	
	/**
	 * Used to close the connection.
	 */
	public void disconnect(){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
