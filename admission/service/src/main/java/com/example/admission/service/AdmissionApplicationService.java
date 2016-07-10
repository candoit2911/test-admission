package com.example.admission.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.example.admission.db.MongoDBService; 
import com.example.admission.resource.Item;
import com.example.admission.resource.Order;
import com.example.admission.resource.OrderItem;
import com.example.admission.resource.Student;
import com.example.admission.utils.TestUtils;


public class AdmissionApplicationService {	
	
	final MongoDBService mongoService;
	final AdmissionConfiguration configuration;
	
	public AdmissionApplicationService(MongoDBService mongoService, AdmissionConfiguration configuration) {
		this.mongoService = mongoService;		
		this.configuration = configuration;
	}
	
	public List<OrderItem> getOrderItems() {		
		return TestUtils.getItems();
		//return mongoService.getOrderItems();
	}
	
	public Student getStudent(int rollNumber) { 
		return TestUtils.getStudent(rollNumber);
		//return mongoService.getStudent(rollNumber);
	}
	
	public Order processOrder(Order order) {	
		
		if(order.getRollNumber() <= 0) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		Student s = TestUtils.getStudent(order.getRollNumber());
		Order returnOrder = new Order();
		List<Item> items = new LinkedList<Item>();
		double totalSurcharge = 0.0;
		double totalCost = 0.0;
		Map<String, OrderItem> itemMap = TestUtils.getItemMap();
		for(Item oItem:order.getOrderedItem()) {
			double itemPrice = itemMap.get(oItem.getName()).getPrice() * oItem.getQuantity();
			double itemSurCharge =  processSurcharge(itemPrice, getSurchargePercentage(s.isManagementStudent(),itemMap.get(oItem.getName()).isSurchargeApplicable()));
			totalSurcharge += itemSurCharge;
			itemPrice += itemSurCharge;
			totalCost += itemPrice;
			items.add(new Item(oItem.getName(), oItem.getDescription(), itemPrice, oItem.getQuantity()));			
		}
		
		returnOrder.setOrderedItem(items);
		returnOrder.setRollNumber(order.getRollNumber());
		returnOrder.setTotalCost(Double.toString(roundOfTwoDecimal(totalCost)));
		returnOrder.setTotalSurcharge(Double.toString(roundOfTwoDecimal(totalSurcharge)));
		//mongoService.insertOrder(returnOrder);
		return returnOrder;		
	}
	
	public static double roundOfTwoDecimal(double amount) {
		return Math.round(amount * 100.0) / 100.0;
	}
	
	public static double processSurcharge( double price, double surcharge) {
		double totalSurcharge = (price * surcharge) / 100.0;
		return roundOfTwoDecimal(Math.round(totalSurcharge * 20.0)/ 20.0);
	}
	
	
	private double getSurchargePercentage(boolean isManagement, boolean isSurChargeApplicable) {
		double surcharge = 0;
		if(isSurChargeApplicable) {
			if(isManagement) {
				surcharge = configuration.surcharge + configuration.extraSurcharge;
			} else {
				surcharge = configuration.surcharge;
			}
		} else {
			if(isManagement) {
				surcharge = configuration.extraSurcharge;
			}
		}		
		return surcharge;
	}
	
}
