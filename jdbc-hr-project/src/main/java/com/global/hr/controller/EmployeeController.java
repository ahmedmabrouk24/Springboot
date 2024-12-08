package com.global.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.model.Employee;
import com.global.hr.repoistory.implementation.EmplyeeJDBCRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	@Qualifier("emplyeeJDBCRepo")
	private EmplyeeJDBCRepo emplyeeJDBCRepo;
	
	@GetMapping("/count")
	public int countEmployees() {
		return emplyeeJDBCRepo.count();
	}
	@GetMapping("/{id}")
	public Employee findById(@PathVariable int id) {
		return emplyeeJDBCRepo.findById(id);
	}
	@GetMapping("/all")
	public List<Employee> findAll() {
		return emplyeeJDBCRepo.findAll();
	}
}
