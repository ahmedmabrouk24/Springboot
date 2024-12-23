package com.global.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
}
