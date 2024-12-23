package com.global.hr.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.Entity.Department;
import com.global.hr.Servise.DepartmentServise;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentServise departmentServise;
	
	@GetMapping("/findbyid/{id}")
	public Department findById(@PathVariable Long id) {
		return departmentServise.findById(id);
	}
	@PostMapping("/insert")
	public Department insert(@RequestBody Department dept) {
		return departmentServise.insert(dept);
	}
	@PutMapping("/update")
	public Department update(@RequestBody Department dept) {
		return departmentServise.update(dept);
	}
}
