package com.global.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.entity.Employee;
import com.global.hr.repoistory.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	public Long countEmp() {
		return employeeRepo.count();
	}
	
	public List <Employee> findByName(String name){
		return employeeRepo.findByName(name);
	}
	
	public Employee findById(Long id) {
		return employeeRepo.findById(id).get();
	}

	public Iterable<Employee> findAll() {
		return employeeRepo.findAll();
	}

	public List<Employee> findById(String name) {
		return employeeRepo.findByName(name);
	}

	public Employee addEmployee(Employee emp) {
		return employeeRepo.save(emp);
	}

	public Employee updateEmployee(Employee emp) {
		return employeeRepo.save(emp);
	}

	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
	}
	
	public int updateSalary(Double salary,Long id) {
		return employeeRepo.updataEmplyeeSalary(salary, id);
	}
}
