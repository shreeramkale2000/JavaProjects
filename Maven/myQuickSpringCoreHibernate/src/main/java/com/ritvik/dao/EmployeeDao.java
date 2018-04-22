package com.ritvik.dao;

import org.springframework.stereotype.Repository;

import com.ritvik.model.Employee;

@Repository("employeeDao")
public class EmployeeDao extends CommonDaoImpl {

	public Integer saveEmployee(Employee emp) {
		return (Integer) getCurrentSession().save(emp);
	}
}
