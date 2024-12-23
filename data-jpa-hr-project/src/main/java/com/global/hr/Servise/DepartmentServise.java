package com.global.hr.Servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.Entity.Department;
import com.global.hr.repository.DepartmentRepo;

@Service
public class DepartmentServise {

	@Autowired
	private DepartmentRepo departmentRepo;
	
	public Department findById(Long id) {
		return departmentRepo.findById(id).orElseThrow();
	}
	public Department insert(Department emp) {
		return departmentRepo.save(emp);
	}
	public Department update(Department emp) {
		Department current = departmentRepo.findById(emp.getId()).get();
		current.setName(emp.getName());
		return departmentRepo.save(current);
	}
}
