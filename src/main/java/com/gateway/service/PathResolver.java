package com.gateway.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@SuppressWarnings("rawtypes")
@Service
public class PathResolver {

	public static final String BASE_API = "/v1/api";
	public static final String USER_SERVICE_PATH = "/user/details";
	public static final String EMPLOYEE_SERVICE_PATH = "/employee/details";
	public static final String EMPLOYEE_SERVICE = "Employee-Details-Application";
	public static final String USER_SERVICE = "User-Details-Application";
	private Map<String, ServiceConnector> serviceConnectorMap;
	
	@Autowired
	@Qualifier("EmployeeService")
	private ServiceConnector<String> employeeConnector;
	@Autowired
	@Qualifier("UserService")
	private ServiceConnector<String> userConnector;
	
	
	public void initilize() {
		
		this.serviceConnectorMap = new HashMap<String, ServiceConnector>();
		this.serviceConnectorMap.put(EMPLOYEE_SERVICE, employeeConnector);
		this.serviceConnectorMap.put(USER_SERVICE, userConnector);
	}
	
	public ServiceConnector resolvePath(final String path) {
		if(MapUtils.isEmpty(this.serviceConnectorMap)) {
			this.initilize();
		}
			
		System.out.println("API GATEWAY : resolvePath " + path);
		return this.serviceConnectorMap.get(path);
	}
}
