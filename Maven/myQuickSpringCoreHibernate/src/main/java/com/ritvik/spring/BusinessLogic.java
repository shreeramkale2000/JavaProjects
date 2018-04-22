package com.ritvik.spring;

import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ritvik.model.Employee;
import com.ritvik.service.EmployeeService;

@Repository("businessLogic")
public class BusinessLogic {

	private Logger logger = Logger.getLogger(BusinessLogic.class);

	@Autowired
	private StringWriter stackTraceWriter;

	@Autowired
	private EmployeeService employeeService;

	public void start() {
		logger.info("Start");
		Integer empId = 0;

		Employee emp = new Employee();
		emp.setName("Ritvik");
		empId = employeeService.saveEmployee(emp);
		logger.info("Employee Saved ->" + empId);

		logger.info("End");
	}

}