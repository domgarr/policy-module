/*
 * Created by Patrick Yu on Aug 14, 2018
 */

package com.policy.data;

public class Policy {
	private int policy_id;
	private String policyType; //Updated by Domenic Garreffa on Aug 15, 2018
	private String policy_name;
	private int number_nominees;
	private double tenure;
	private double sum_assured;
	private String pre_reqs;
	
	public int getPolicyId() {
		return policy_id;
	}

	
	//Added by Domenic Garreffa on Aug 15, 2018
	public String getPolicyType() {
		return policyType;
	}

	//Added by Domenic Garreffa on Aug 15, 2018
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public void setPolicyId(int id) {
		this.policy_id = id;
	}

	public String getPolicyName() {
		return policy_name;
	}

	public void setPolicyName(String name) {
		this.policy_name = name;
	}

	public int getNumberNominees() {
		return number_nominees;
	}

	public void setNumberNominees(int numberOfNominees) {
		this.number_nominees = numberOfNominees;
	}

	public double getTenure() {
		return tenure;
	}

	public void setTenure(double tenure) {
		this.tenure = tenure;
	}

	public double getSumAssured() {
		return sum_assured;
	}

	public void setSumAssured(double sum) {
		this.sum_assured = sum;
	}

	public String getPreReqs() {
		return pre_reqs;
	}

	public void setPreReqs(String preReqs) {
		this.pre_reqs = preReqs;
	}
}
