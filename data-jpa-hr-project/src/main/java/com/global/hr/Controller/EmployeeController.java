package com.global.hr.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.Entity.Employee;
import com.global.hr.Servise.EmplyeeServise;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmplyeeServise emplyeeServise;
	
	@GetMapping("/findall")
	public List<Employee> findAll() {
		return emplyeeServise.findAll();
	}
	@GetMapping("/findbyid/{id}")
	public Employee findById(@PathVariable Long id) {
		return emplyeeServise.findById(id);
	}
	@PostMapping("/insert")
	public Employee insert(@RequestBody Employee emp) {
		return emplyeeServise.insert(emp);
	}
	@PutMapping("/update")
	public Employee update(@RequestBody Employee emp) {
		return emplyeeServise.update(emp);
	}
	@GetMapping("/department/{deptId}")
	public List<Employee> findByDepartmentId(@PathVariable Long deptId){
		return emplyeeServise.findByDepartmentId(deptId);
	}
	@GetMapping("/counts")
	public ResponseEntity<?> hrStatisiticProjection() {
		return ResponseEntity.ok(emplyeeServise.hrStatisiticProjection());
	}
	@GetMapping("/filter")
	public ResponseEntity<?> filter(@RequestParam String name, @RequestParam int pageNum, 
			@RequestParam int pageSize, @RequestParam boolean isAsc, @RequestParam String sortCol){
		return ResponseEntity.ok(emplyeeServise.filter(name, pageNum, pageSize, isAsc, sortCol));
	}
}
