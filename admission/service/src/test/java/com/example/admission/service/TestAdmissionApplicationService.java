package com.example.admission.service;

import org.junit.Assert;
import org.junit.Test;

import com.example.admission.service.AdmissionApplicationService;

public class TestAdmissionApplicationService {
	@Test
	public void testSurcharge() {		
		Assert.assertEquals(0, Double.compare(4.2, AdmissionApplicationService.processSurcharge(27.99, 15)));
	}
}
