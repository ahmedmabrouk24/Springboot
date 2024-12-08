package com.global.hr.repoistory.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.global.hr.mapper.EmployeeMapper;
import com.global.hr.model.Employee;
import com.global.hr.repoistory.EmployeeRepo;

@Component
@Qualifier("emplyeeJDBCRepo")
public class EmplyeeJDBCRepo implements EmployeeRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select count(*) from employees", Integer.class);
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select id, name, salary from employees where id = ?",new Object[] {id}
		, new EmployeeMapper());
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select id, name, salary from employees", new EmployeeMapper());
	}

	@Override
	public void insert(Employee emp) {
		jdbcTemplate.update("insert into employees (id, name, salary) values(?, ?, ?)"
				, new Object[] {emp.getId(), emp.getName(), emp.getSalary()});
		
	}

	@Override
	public void update(Employee emp) {
		jdbcTemplate.update("update emplyees set id = ?, name = ?, salary = ?"
				, new Object[] {emp.getId(), emp.getName(), emp.getSalary()});
		
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update("delete emplyees where id = ?"
				, new Object[] {id});
	}


}
