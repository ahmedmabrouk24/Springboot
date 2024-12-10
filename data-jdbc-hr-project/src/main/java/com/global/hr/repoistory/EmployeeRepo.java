package com.global.hr.repoistory;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long>{
	
	@Query(value = "select * from employees where name = :empName")
	List <Employee> findByName(@Param("empName") String name);
	
	@Modifying
	@Query("update employees set salary = :empsalary where id = :empid")
	int updataEmplyeeSalary(@Param("empsalary") Double salary, @Param("empid") Long id);
}
