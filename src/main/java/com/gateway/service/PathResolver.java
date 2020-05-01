package com.gateway.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gateway.service.implementation.EmployeeServiceConnectorImpl;
import com.gateway.service.implementation.UserServiceConnectorImpl;

@SuppressWarnings("rawtypes")
@Service
public class PathResolver {

	public static final String BASE_API = "/v1/api";
	public static final String USER_SERVICE_PATH = "/user/details";
	public static final String EMPLOYEE_SERVICE_PATH = "/employee/details";
	public static final String EMPLOYEE_SERVICE = "Employee-Details-Application";
	public static final String USER_SERVICE = "UserServcice";
	private final Map<String, ServiceConnector> serviceConnectorMap;
	
	public PathResolver() {
		this.serviceConnectorMap = new HashMap<String, ServiceConnector>();
		this.serviceConnectorMap.put(EMPLOYEE_SERVICE, new EmployeeServiceConnectorImpl());
		this.serviceConnectorMap.put(USER_SERVICE, new UserServiceConnectorImpl());
	}
	
	public ServiceConnector resolvePath(final String path) {
		return this.serviceConnectorMap.get(path);
	}
}
