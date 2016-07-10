package com.example.admission.resource;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.vz.mongodb.jackson.ObjectId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
	
	private int rollNumber;
	
	private String name;
	
	private boolean managementStudent;
	
	public Student(){
		
	}	

	public Student(int rollNumber, String name, boolean managementStudent) {
		this.rollNumber = rollNumber;
		this.name = name;
		this.managementStudent = managementStudent;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isManagementStudent() {
		return managementStudent;
	}

	public void setManagementStudent(boolean managementStudent) {
		this.managementStudent = managementStudent;
	}
	

}
