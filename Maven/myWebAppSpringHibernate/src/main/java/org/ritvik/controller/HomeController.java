package org.ritvik.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.ritvik.model.Employee;
import org.ritvik.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/HomeController")
@Scope("request")
public class HomeController {
	
	@Autowired
	private Logger logger;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model, Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		logger.debug("User Logged - " + userDetails.getUsername());
		
		Employee emp = new Employee();
		emp.setName("Meenal");
		Integer i = employeeService.saveEmployee(emp);
		logger.debug("Employee Saved " + i);
		
		return "/welcome.htm";
	}
	
}