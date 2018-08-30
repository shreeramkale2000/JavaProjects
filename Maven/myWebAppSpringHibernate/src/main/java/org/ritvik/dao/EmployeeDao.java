package org.ritvik.dao;

import org.ritvik.model.Employee;
import org.springframework.stereotype.Repository;

@Repository("employeeDao")
public class EmployeeDao extends CommonDaoImpl {

	public Integer saveEmployee(Employee emp) {
		return (Integer) getCurrentSession().save(emp);
	}
}
