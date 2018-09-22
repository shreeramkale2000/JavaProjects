package org.ritvik.controller;

import java.security.Principal;

import org.apache.log4j.Logger;
import org.ritvik.model.Employee;
import org.ritvik.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/HomeController")
public class HomeController {
	
	@Autowired
	private Logger logger;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model, Principal principal) {
		logger.debug("User Logged - " + principal.getName());
		
		Employee emp = new Employee();
		emp.setName("Meenal");
		Integer i = employeeService.saveEmployee(emp);
		logger.debug("Employee Saved " + i);
		
		return "/welcome.htm";
	}
	
}