package com.gateway.api;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.service.PathResolver;

@RestController
@RequestMapping("/v1/api")
@SuppressWarnings("unchecked")
public class GatewayController {
	
  	@Autowired
  	private PathResolver pathResolver;
  		
	@RequestMapping(value = "/user/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> getUserDetails(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userDob") String userDob) throws Exception {
		
		System.out.println("API GATEWAY : getUserDetails ");
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userDob", userDob);
		queryMap.put("userName", userName);
			
		return pathResolver.resolvePath(PathResolver.USER_SERVICE).execute(queryMap);
	}

	@RequestMapping(value = "/employee/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<String> getEmployeeDetails(@RequestParam(name = "empEmail") String empEmail) throws Exception {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("empEmail", empEmail);
		System.out.println("API GATEWAY : getEmployeeDetails ");
		return pathResolver.resolvePath(PathResolver.EMPLOYEE_SERVICE).execute(queryMap);
	}
	
}
