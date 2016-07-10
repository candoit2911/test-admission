package com.example.admission.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.example.admission.service.AdmissionApplicationService;

@Path("/admission")
@Produces(MediaType.APPLICATION_JSON)
public class AdmissionResource {

	private final AdmissionApplicationService service;

	public AdmissionResource(AdmissionApplicationService service) {
		this.service = service;
	}

	@GET
	@Path("get-items")
	public List<OrderItem> getItems() {
		return service.getOrderItems();
	}

	@GET
	@Path("get-student")
	public Student getStudent(@QueryParam("rollNumber") int rollNumber) {
		return service.getStudent(rollNumber);
	}

	@POST
	@Path("process-order")
	public Order processOrder(Order order) {
		return service.processOrder(order);
	}

}
