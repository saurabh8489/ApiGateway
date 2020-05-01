package com.gateway.service;

import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.web.client.RestTemplate;

public interface ServiceConnector<T> {
	
	Response execute(Map<String, String> queryParams);
	
	default T executeCall(RestTemplate restTemplate, String serviceName, String apiPath, Map<String, String> queryParams, Class<T> responseClass) {
		return restTemplate.getForObject("http://" + serviceName + apiPath , responseClass, queryParams);
	} 
	

}
