package com.gateway.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface ServiceConnector<T> {

	ResponseEntity<T>  execute(Map<String, String> queryParams);

	default T executeCall(RestTemplate restTemplate, String serviceName, String apiPath, Map<String, String> queryParams, Class<T> responseClass) {

		System.out.println("API GATEWAY : execute " + "http://" + serviceName + apiPath + formatQueryParams(queryParams));
		return restTemplate.getForObject("http://" + serviceName + apiPath + formatQueryParams(queryParams), responseClass);
	}

	static String formatQueryParams(Map<String, String> params) {
		return params.entrySet().stream().map(p -> p.getKey() + "=" + p.getValue()).reduce((p1, p2) -> p1 + "&" + p2)
				.map(s -> "?" + s).orElse("");
	}
	
}
