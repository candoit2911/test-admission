package com.example.admission.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.vz.mongodb.jackson.ObjectId;


public class OrderItem extends Item {
	
	@ObjectId
	@JsonProperty("_id")
	private String id; 
	
	private boolean isSurchargeApplicable;	

	public OrderItem() {
		
	}
	
	public OrderItem(String name, String description, double price, int quantity, boolean isSurchargeApplicable) {
		super(name, description, price, quantity);
		this.isSurchargeApplicable = isSurchargeApplicable;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public boolean isSurchargeApplicable() {
		return isSurchargeApplicable;
	}

	public void setSurchargeApplicable(boolean isSurchargeApplicable) {
		this.isSurchargeApplicable = isSurchargeApplicable;
	}

}
