package com.example.admission.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	
    private String name;
	
	private String description;
	
	private double price;
	
	private int quantity = 1;
	
	public Item() {
		
	}

	
	public Item(String name, String description, double price, int quantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
