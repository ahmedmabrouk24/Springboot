package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.Entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	
}
