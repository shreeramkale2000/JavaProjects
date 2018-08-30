package org.ritvik.service;

import org.ritvik.dao.EmployeeDao;
import org.ritvik.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeService")
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
     
    public Integer saveEmployee(Employee emp) {
    	return employeeDao.saveEmployee(emp);
    }
}
