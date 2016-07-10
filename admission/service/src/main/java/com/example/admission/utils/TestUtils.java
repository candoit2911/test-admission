package com.example.admission.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.admission.resource.OrderItem;
import com.example.admission.resource.Student;

public class TestUtils {
	
	private static final List<String> names = Arrays.asList("alice", "bob", "charlie", "dave", "eric");
	
	private static String getStudentName(int rollNumber) {		
		return names.get(rollNumber % names.size()) + rollNumber;
	}
	
	private static boolean isManagementStudent(int rollNumber) {
		return rollNumber%2 == 0;
	}
	
	public static Student getStudent(int rollNumber) {
		
		return new Student(rollNumber, getStudentName(rollNumber), isManagementStudent(rollNumber));
	}
	
	public static List<OrderItem> getItems() {		
		return Arrays.asList(
				new OrderItem("SHIRT","SCHOOL SHIRT", 27.99, 1, true),
				new OrderItem("TROUSER","SCHOOL TROUSER", 18.99, 1, true),
				new OrderItem("PHYSICS_BOOK","PHYSICS BOOK", 9.75, 1, true),
				new OrderItem("CHEMISTRY_BOOK","CHEMISTRY BOOK", 9.75, 1, true),
				new OrderItem("MATH_BOOK","MATH BOOK", 9.75, 1, true),
				new OrderItem("TIE","SCHOOL TIE", 11.25, 1, true)		
				);
	}
	
	public static Map<String, OrderItem> getItemMap() {
		Map<String, OrderItem> map = new HashMap<String, OrderItem>();
		for(OrderItem i : getItems()) {
			map.put(i.getName(), i);
		}		
		return map;
	}

}
