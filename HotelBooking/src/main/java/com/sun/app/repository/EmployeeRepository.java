package com.sun.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sun.app.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
	public Employee findByUserName(String userName);
	
}
