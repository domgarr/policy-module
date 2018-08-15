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
 * Example: ResultSet test = OracleConnection.INSTANCE.executeQuery(query)
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
	 * connect opens a connection to the oracle database. It will be called whenever
	 * the database is accessed.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void connect() throws ClassNotFoundException, SQLException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uname = "system";
		String pwd = "tcs12345";
		
		//step 2
		con = DriverManager.getConnection(url, uname, pwd);
		
		st = con.createStatement();
	}
	
	/**
	 * Use this method to make changes to the database only. Will not work for 
	 * retrieving information from database.
	 * @param update The SQL command to be run
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean executeUpdate(String update) throws ClassNotFoundException, SQLException {
		connect();
		try {
			st.executeUpdate(update);
			return true;
		}
		catch (SQLException e) {
			return false;
		}
		finally {
			disconnect();
		}
	}
	
	/**
	 * Use this method to retrieve information from the database only. Cannot be
	 * used to change database in any way.
	 * 
	 * IMPORTANT!!!!! CALL DISCONNECT WHEN DONE WITH RESULTSET
	 * 
	 * 
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
	 * Used to close the connection.
	 */
	public void disconnect(){
		try {
			rs.close();
		} catch (SQLException e) {}
		try {
			st.close();
		} catch (SQLException e) {}
		try {
			pst.close();
		} catch (SQLException e) {}
		try {
			con.close();
		} catch (SQLException e) {}
	}

}
