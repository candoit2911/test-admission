package com.example.admission.resource;

import java.util.LinkedList;
import java.util.List;

import net.vz.mongodb.jackson.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
	
	private int rollNumber;
	
	private List<Item> orderedItem = new LinkedList<Item>(); 
	
	private String totalSurcharge;
	
	private String totalCost;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public List<Item> getOrderedItem() {
		return orderedItem;
	}

	public void setOrderedItem(List<Item> orderedItem) {
		this.orderedItem = orderedItem;
	}

	public String getTotalSurcharge() {
		return totalSurcharge;
	}

	public void setTotalSurcharge(String totalSurcharge) {
		this.totalSurcharge = totalSurcharge;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", rollNumber=" + rollNumber
				+ ", orderedItem=" + orderedItem + ", totalSurcharge="
				+ totalSurcharge + ", totalCost=" + totalCost + "]";
	}
	
	
	
}
