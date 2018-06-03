package com.ritvik.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ritvik.model.Employee;
import com.ritvik.service.EmployeeService;

@Repository("businessLogic")
public class BusinessLogic {

	private Logger logger = Logger.getLogger("org.ritvik.processLog");

	@Autowired
	private EmployeeService employeeService;

	public void start() {
		logger.debug("Start");
		Integer empId = 0;

		Employee emp = new Employee();
		emp.setName("Ritvik");
		empId = employeeService.saveEmployee(emp);
		logger.debug("Employee Saved ->" + empId);

		logger.debug("End");
	}

}