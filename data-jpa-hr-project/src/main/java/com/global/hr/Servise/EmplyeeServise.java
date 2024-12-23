package com.global.hr.Servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.global.hr.HrStatisiticProjection;
import com.global.hr.Entity.Employee;
import com.global.hr.repository.EmployeeRepo;

@Service
public class EmplyeeServise {

	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private DepartmentServise departmentServise;
	@Autowired
	private UserServise userServise;
	public List<Employee> findAll() {
		return employeeRepo.findAll();
	}
	public Employee findById(Long id) {
		return employeeRepo.findById(id).orElseThrow();
	}
	public Employee insert(Employee emp) {
		if (emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			emp.setDepartment(departmentServise.findById(emp.getDepartment().getId()));
		}
		if (emp.getUser() != null && emp.getUser().getId() != null) {
			emp.setUser(userServise.findById(emp.getUser().getId()));
		}
		return employeeRepo.save(emp);
	}
	public Employee update(Employee emp) {
		Employee current = employeeRepo.findById(emp.getActor_id()).orElseThrow();
		current.setFirstName(emp.getFirstName());
		current.setLastName(emp.getLastName());
		//current.setSalary(emp.getSalary());
		//current.setDepartment(emp.getDepartment());
		return employeeRepo.save(current);
	}
	public List<Employee> findByDepartmentId(Long deptId){
		return employeeRepo.findByDepartmentId(deptId);
	}
	public HrStatisiticProjection hrStatisiticProjection() {
		return employeeRepo.hrStatisiticProjection();
	}
	public Page<Employee> filter(String name, int pageNum, int pageSize, boolean isAsc, String sortCol){
		if (name.isEmpty() || name.isBlank()) {
			name = null;
		}
		Pageable page = PageRequest.of(pageNum, pageSize, Sort.by(isAsc ? Direction.ASC : Direction.DESC, sortCol));
		return employeeRepo.filter(name, page);
	}
}
