package com.policy.dao;

import java.sql.*;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import com.policy.data.Policy;

/*
 * Created by Domenic Gareffa @ 3:02pm on August 15, 2018
 * 
 * This is a Data Access Object. It will communicate with the Database and return
 * data. More specifically this class will associate itself with the Policy Table.
 */

/**
 * NOTE: We have added a column to the existing Policy Table, 
 *  we have added sum_assured_min
 * 	and altered sum_assured to sum_assured_min
 * 
 * create table Policies (
	policy_id int primary key,
	policy_type varchar(255) not null, -- Accidential, whole life, term, pension
	policy_name varchar(255) not null,
	number_nominees int not null,
	tenure double precision not null,
	sum_assured_min double precision not null,
	sum_assured_max double precision not null,
	pre_reqs varchar(255) not null
);
 * 
 * 
 * @author 1559831
 *
 */

public class PolicyDao {

	private final String tableName = "Policies";
	private final String SELECT_POLICY_BY_ID = "select * from Policies where policy_id = ?";
	private final String INSERT_INTO_POLICY = "insert into " + tableName + " values(?,?,?,?,?,?,?,?)";
	private final String SELECT_ALL_POLICY_NAME_AND_POLICY_ID = "select policy_name, policy_id from POLICIES";
	private final String SELECT_MAX_ID = "select MAX(policy_id) from " + tableName;
	private final String UPDATE_POLICY = "UPDATE Policies" +
			"SET policy_type = ?, "+
			"policy_name = ?, " + 
			"number_nominees = ?, " +
			"tenure = ?, " +
			"sum_assured_min = ?, " +
			"sum_assured_max = ?, " +
			"pre_reqs = ? " +
			"WHERE policy_id = ? ";
	
	/**
	 *  Will insert a policy object into the database.
	 * @param policy - an instantiated Policy object.
	 * @return true if policy is successfully added or false if not.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean insert(Policy policy) throws SQLException, ClassNotFoundException {
			Connection con = null;
			PreparedStatement ps = null;
			
			con = OracleConnection.INSTANCE.getConnection();
		
			ps = con.prepareStatement(INSERT_INTO_POLICY);
			
			ps.setInt(1, policy.getPolicyId());
			ps.setString(2, policy.getPolicyType());
			ps.setString(3, policy.getPolicyName());
			ps.setInt(4, policy.getNumberNominees());
			ps.setDouble(5, policy.getTenure());
			ps.setDouble(6, policy.getMinSum());
			ps.setDouble(7, policy.getMaxSum());
			ps.setString(8, policy.getPreReqs());

			int rowsAffected = ps.executeUpdate();
			
			//clean up
			ps.close();
			OracleConnection.INSTANCE.disconnect();
				
			if(rowsAffected >= 1) {
				System.out.println("Policy successfully added");
				return true;
			}else {
				System.out.println("Policy was not added");
				return false;
			}
	}
	
	private Policy getPolicyInformation(ResultSet rs) throws SQLException {
		Policy p = new Policy();
		p.setPolicyId(rs.getInt("policy_id"));
		p.setPolicyName(rs.getString("policy_name"));
		p.setTenure(rs.getDouble("tenure"));
		p.setMinSum(rs.getDouble("sum_assured_min"));
		p.setMaxSum(rs.getDouble("sum_assured_max"));
		p.setPaymentsPerYear(rs.getInt("payments_per_year"));
		p.setPremiumAmonut(rs.getDouble("premium_amount"));
		return p;
	}
	
	
	public ArrayList<Policy> getPoliciesByCustomerID(int id) throws ClassNotFoundException, SQLException{
		ArrayList<Policy> policies = new ArrayList<Policy>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		con = OracleConnection.INSTANCE.getConnection();
		String query = "SELECT *\r\n" + 
				"FROM PolicyMap\r\n" + 
				"LEFT JOIN Policies\r\n" + 
				"ON PolicyMap.policy_ID=Policies.policy_ID\r\n" + 
				"Where PolicyMap.customer_ID =" + id + ";";
		ps = con.prepareStatement(query);
		rs = ps.executeQuery();
		
		while(rs.next()){
			policies.add(getPolicyInformation(rs));
		}
		rs.close();
		ps.close();
		OracleConnection.INSTANCE.disconnect();
		return policies;
	}
	
	/**
	 * 
	 * @return An integer holding the largest ID currently in the table.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Integer getLargestID() throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		con = OracleConnection.INSTANCE.getConnection();
		ps = con.prepareStatement(SELECT_MAX_ID);
		rs = ps.executeQuery();
		
		rs.next();
		int maxID = rs.getInt(1);
		
		//clean up
		rs.close();
		ps.close();
		OracleConnection.INSTANCE.disconnect();
		
		return maxID;
}
	/**
	 * Added by Domenic Garreffa on Aug 16, 2018
	 * 
	 * Updates existing Policy where ID matches method paramter.
	 * @param policy
	 * @return True if Policy table was affected. IE. Policy was altered succesfully and false otherwise.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean update(Policy policy, int policyID) throws SQLException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		
		con = OracleConnection.INSTANCE.getConnection();
	
		ps = con.prepareStatement(UPDATE_POLICY);
		
		ps.setString(1, policy.getPolicyType());
		ps.setString(2, policy.getPolicyName());
		ps.setInt(3, policy.getNumberNominees());
		ps.setDouble(4, policy.getTenure());
		ps.setDouble(5, policy.getMinSum());
		ps.setDouble(6, policy.getMaxSum());
		ps.setString(7, policy.getPreReqs());
		ps.setInt(8, policyID);

		int rowsAffected = ps.executeUpdate();
		
		//clean up
		ps.close();
		con.close();
			
		if(rowsAffected >= 1) {
			System.out.println("Policy successfully updated");
			return true;
		}else {
			System.out.println("Policy was not updated.");
			return false;
		}
	}
	
	/**
	 * Method to return a List of Policies. The list will simply contain every policy
	 * in the database. 
	 * 
	 * Created by Nicholas Kauldhar on August 16 around 2pm
	 * Updated by Nicholas Kauldhar August 17 around 9am since schema change
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static List<Policy> getAllPolicies () throws SQLException, ClassNotFoundException {
		Connection con = OracleConnection.INSTANCE.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("Select * from Policies");
		
		
		List<Policy> k = new ArrayList<Policy>();
		
		Policy temp;
		while (rs.next()) {
			temp = new Policy();
			temp.setPolicyId(rs.getInt(1));
			temp.setPolicyType(rs.getString(2));
			temp.setPolicyName(rs.getString(3));
			temp.setNumberNominees(rs.getInt(4));
			temp.setTenure(rs.getDouble(5));
			temp.setMinSum(rs.getDouble(6));
			temp.setMaxSum(rs.getDouble(7));
			temp.setPreReqs(rs.getString(8));
			k.add(temp);
		}
		OracleConnection.INSTANCE.disconnect();
		System.out.println(k.get(0).getPolicyId());
		
		return k;
		
	}

	/**
	 * Method used by Admin to generate certificates. It uses customer and 
	 * policy ID to find a PolicyMap. It then stores the ID of that policy map
	 * in the session object to be further used in other methods. Returns true if
	 * a PolicyMap is returned and false otherwise.
	 * 
	 * Created by Nicholas Kauldhar on August 16 around 3pm
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean searchByCustandPolicy(HttpServletRequest request) throws SQLException, ClassNotFoundException {
		String custid = request.getParameter("customerID");
		System.out.println(custid);
		int c = -1;
		int d = -1;
		String polid = request.getParameter("policyID");
		System.out.println(polid);
		try {
			c = Integer.parseInt(custid);
			d = Integer.parseInt(polid);
		}
		catch(Exception e) {
			return false;
		}
		
		try{
			Connection con = OracleConnection.INSTANCE.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from PolicyMap where customer_id = " 
			+ c + " and policy_id = " + d);
			if(rs.next()) {
				request.getSession().setAttribute("certificateMapID", rs.getInt(1));
				return true;
			}
			else {
				return false;
			}
			
		
		}
		catch (Exception e) {
			return false;
		}
		finally {
			OracleConnection.INSTANCE.disconnect();
		}
		
	}
	
	public static void main (String[] args) throws ClassNotFoundException, SQLException {
		List<Policy> k = getAllPolicies();
		System.out.println(k.get(0).getNumberNominees());
	}
	
	public ArrayList<String> selectAllPolicyNameAndPolicyID() throws ClassNotFoundException, SQLException{
 		System.out.println("CHECK");
 		Connection con = null;
 		PreparedStatement ps = null;
 		ResultSet rs = null;
 		
 		con = OracleConnection.INSTANCE.getConnection();
 		ps = con.prepareStatement(SELECT_ALL_POLICY_NAME_AND_POLICY_ID);
 		rs = ps.executeQuery();
 		
 		ArrayList<String> policyNameAndIDConcatList = new ArrayList<>();
 		while(rs.next()) {
 			policyNameAndIDConcatList.add(rs.getString(1) + '(' + rs.getString((2)) + ')');
 		}
 		
 		//clean up
 		ps.close();
 		con.close();
 		rs.close();
 			
 		if(!policyNameAndIDConcatList.isEmpty()) {
 			System.out.println("Policies successfully retrieved.");
 			return policyNameAndIDConcatList;
 		}else {
 			System.out.println("No policies retrieved.");
 			return null;
 		}
 	}
 	
 	/**
 	 * Get a policy object given an ID
 	 * @param ID
 	 * @return
 	 * @throws ClassNotFoundException
 	 * @throws SQLException
 	 */
 	public Policy selectAllPolicyByID(int ID) throws ClassNotFoundException, SQLException{
 		Connection con = null;
 		PreparedStatement ps = null;
 		ResultSet rs = null;
 		
 		con = OracleConnection.INSTANCE.getConnection();
 		ps = con.prepareStatement(SELECT_POLICY_BY_ID);		
 		ps.setInt(1, ID);
 		rs = ps.executeQuery();

 		Policy policy = null;
 		
 		if(rs.next()) {
 		policy = new Policy();
 		policy.setPolicyId(rs.getInt(1));
 		policy.setPolicyType(rs.getString(2));
 		policy.setPolicyName(rs.getString(3));
 		policy.setNumberNominees(rs.getInt(4));
 		policy.setTenure(rs.getDouble(5));
 		policy.setMinSum(rs.getDouble(6));
 		policy.setMaxSum(rs.getDouble(7));
 		policy.setPreReqs(rs.getString(8));	
 		}

 		//clean up
 		ps.close();
 		con.close();
 		rs.close();
 			
 		if(policy != null) {
 			System.out.println("Policy found.");
 			return policy;
 		}else {
 			System.out.println("No policies found.");
 			return null;
 		}
 	}

}
