package com.global.hr.Servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.Entity.User;
import com.global.hr.repository.UserRepo;

@Service
public class UserServise {

	@Autowired
	private UserRepo userRepo;
	public List<User> findAll() {
		return userRepo.findAll();
	}
	public User findById(Long id) {
		return userRepo.findById(id).orElseThrow();
	}
	public User insert(User emp) {
		return userRepo.save(emp);
	}
	public User update(User emp) {
		User current = userRepo.findById(emp.getId()).get();
		current.setUserName(emp.getUserName());
		current.setPassword(emp.getPassword());
		return userRepo.save(current);
	}
}
