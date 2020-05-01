package com.gateway.api;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
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
  		
	@RequestMapping(value = "/user/details", method = RequestMethod.GET)
	public Response getUserDetails(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userDob") String userDob, ServletContext context) throws Exception {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("userDob", userDob);
		queryMap.put("userName", userName);
			
		return pathResolver.resolvePath(context.getContextPath()).execute(queryMap);
	}

	@RequestMapping(value = "/employee/details", method = RequestMethod.GET)
	public Response getEmployeeDetails(@RequestParam(name = "empEmail") String empEmail, ServletContext context) throws Exception {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("empEmail", empEmail);
			
		return pathResolver.resolvePath(context.getContextPath()).execute(queryMap);
	}
	
}
