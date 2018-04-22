package com.ritvik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ritvik.dao.EmployeeDao;
import com.ritvik.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
     
    public Integer saveEmployee(Employee emp) {
    	return employeeDao.saveEmployee(emp);
    }
}
