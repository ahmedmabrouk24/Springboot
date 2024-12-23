package com.global.hr.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.global.hr.HrStatisiticProjection;
import com.global.hr.Entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{
	public List<Employee> findByDepartmentId(Long deptId);
	
	@Query(value = "select"
			+ "	(select count(*) from hr_departments) deptCount,"
			+ "    (select count(*) from hr_employees) empCount,"
			+ "    (select count(*) from users) userCount", nativeQuery = true)
	public HrStatisiticProjection hrStatisiticProjection();
	
	//JPQL
	@Query(value = "select emp from Employee emp where (:empName is null or emp.firstName = :empName)")
	//List<Employee> filter(@Param("empName") String name, Sort sort);
	Page<Employee> filter(@Param("empName") String name, Pageable pageable);
}
