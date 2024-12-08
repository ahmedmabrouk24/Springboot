package com.global.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.global.hr.model.Employee;
import com.global.hr.repoistory.EmployeeRepo;

@Component
public class StartUpProject implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("emplyeeNamedParameterJDBCRepo")
	private EmployeeRepo employeeRepo;
	@Override
	public void run(String... args) throws Exception {
		
		if (employeeRepo.count() == 0) {
			jdbcTemplate.execute("DROP TABLE IF EXISTS employees");
			jdbcTemplate.execute("CREATE TABLE employees (id SERIAL, name VARCHAR(225), salary NUMERIC(15,2))");
			employeeRepo.insert(new Employee(1, "ahmed", 10000.00));
			employeeRepo.insert(new Employee(2, "mohamed", 3155.00));
			employeeRepo.insert(new Employee(3, "ali", 7465.00));
			employeeRepo.insert(new Employee(4, "yasser", 9885.00));
			employeeRepo.insert(new Employee(5, "karem", 1254.00));
		}
		
	}

}
