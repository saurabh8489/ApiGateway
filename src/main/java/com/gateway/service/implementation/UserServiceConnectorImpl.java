package com.gateway.service.implementation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gateway.service.PathResolver;
import com.gateway.service.ServiceConnector;

@Component("UserService")
public class UserServiceConnectorImpl implements ServiceConnector<String> {
	@Autowired
    private RestTemplate restTemplate;

	@Override
	public ResponseEntity<String> execute(Map<String, String> queryParams) {
		HttpStatus status;
		String response;
		try {
			response = ServiceConnector.super.executeCall(restTemplate, PathResolver.USER_SERVICE, PathResolver.USER_SERVICE_PATH, queryParams, String.class);
			
			System.out.println("API GATEWAY : execute " + response);
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response = e.getMessage();
		}
		return ResponseEntity.status(status).body(response);
	}

}
