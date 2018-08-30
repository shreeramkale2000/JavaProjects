package org.ritvik.controller;

import org.apache.log4j.Logger;
import org.ritvik.model.Employee;
import org.ritvik.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/TestControllerModel")
public class TestControllerModel {
	
	@Autowired
	private Logger logger;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		logger.debug("TestControllerModel called...");
		
		Employee emp = new Employee();
		emp.setName("Meenal");
		Integer i = employeeService.saveEmployee(emp);
		logger.debug("Emp Save" + i);
		
		return "/index.htm";
	}
	
}