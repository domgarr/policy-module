/*
 * Created by Patrick Yu on Aug 14, 2018
 */

package com.policy.data;

public class Nominee {
	private int nominee_id;
	private String nominee_name;
	private String relationship_to_customer;
	private String purpose_of_changed;
//	private double percentage;

	public int getNomineeId() {
		return nominee_id;
	}

	public void setNomineeId(int id) {
		this.nominee_id = id;
	}

	public String getNomineeName() {
		return nominee_name;
	}

	public void setNomineeName(String name) {
		this.nominee_name = name;
	}

	public String getRelationshipToCustomer() {
		return relationship_to_customer;
	}

	public void setRelationshipToCustomer(String relationship) {
		this.relationship_to_customer = relationship;
	}

	public String getPurposeOfChanged() {
		return purpose_of_changed;
	}

	public void setPurposeOfChanged(String purpose) {
		this.purpose_of_changed = purpose;
	}

//	public double getPercentage() {
//		return percentage;
//	}
//
//	public void setPercentage(double percentage) {
//		this.percentage = percentage;
//	}
}
