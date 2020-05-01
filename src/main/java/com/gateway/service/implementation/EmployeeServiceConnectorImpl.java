package com.gateway.service.implementation;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gateway.service.PathResolver;
import com.gateway.service.ServiceConnector;

@Component("EmployeeService")
public class EmployeeServiceConnectorImpl implements ServiceConnector<String> {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Response execute(Map<String, String> queryParams) {
		Status status;
		String response;
		try {
			response = ServiceConnector.super.executeCall(restTemplate, PathResolver.EMPLOYEE_SERVICE,PathResolver.EMPLOYEE_SERVICE_PATH, 
					queryParams, String.class);
			status = Status.OK;
			
		} catch (Exception e) {
			status = Status.INTERNAL_SERVER_ERROR;
			response = e.getMessage();
		}
		return Response.status(status).entity(response).build();
	}

}
