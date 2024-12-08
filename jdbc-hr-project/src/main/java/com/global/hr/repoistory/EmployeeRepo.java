package com.global.hr.repoistory;

import java.util.List;

import com.global.hr.model.Employee;

public interface EmployeeRepo {
	int count();
	Employee findById(int id);
	List<Employee> findAll();
	void insert(Employee emp);
	void update(Employee emp);
	void delete(int id);
}
