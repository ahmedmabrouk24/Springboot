package com.global.hr.repoistory.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.global.hr.mapper.EmployeeMapper;
import com.global.hr.model.Employee;
import com.global.hr.repoistory.EmployeeRepo;

@Component
@Qualifier("emplyeeNamedParameterJDBCRepo")
public class EmplyeeNamedParameterJDBCRepo implements EmployeeRepo {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		//return namedParameterJdbcTemplate.queryForObject("select count(*) from employees", Integer.class);
		return 0;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject("select id, name, salary from employees where id = :id",
				mapSqlParameterSource , new EmployeeMapper());
	}
	
	public List<Employee> findByNameAndSalary(String name, double salary) {
		// TODO Auto-generated method stub
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("name", name);
		mapSqlParameterSource.addValue("salary", salary);
		return namedParameterJdbcTemplate.query("select id, name, salary from employees where name = :name, salary = :salary",
				mapSqlParameterSource , new EmployeeMapper());
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return namedParameterJdbcTemplate.query("select id, name, salary from employees", new EmployeeMapper());
	}

	@Override
	public void insert(Employee emp) {
		namedParameterJdbcTemplate.update("insert into employees (id, name, salary) values(:id, :name, :salary)"
				, new BeanPropertySqlParameterSource(emp));
		
	}

	@Override
	public void update(Employee emp) {
		BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(emp);
		namedParameterJdbcTemplate.update("update emplyees set name = :name, salary = :salary"
				, beanPropertySqlParameterSource);
		
	}

	@Override
	public void delete(int id) {
		namedParameterJdbcTemplate.update("delete emplyees where id = :id"
				, new MapSqlParameterSource("id", id));
	}


}
