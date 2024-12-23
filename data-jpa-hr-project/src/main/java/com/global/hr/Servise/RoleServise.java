package com.global.hr.Servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.Entity.Role;
import com.global.hr.repository.RoleRepo;

@Service
public class RoleServise {

	@Autowired
	private RoleRepo roleRepo;
	public List<Role> findAll() {
		return roleRepo.findAll();
	}
	public Role findById(Long id) {
		return roleRepo.findById(id).orElseThrow();
	}
	public Role insert(Role emp) {
		return roleRepo.save(emp);
	}
	public Role update(Role emp) {
		Role current = roleRepo.findById(emp.getId()).get();
		current.setRoleName(emp.getRoleName());
		return roleRepo.save(current);
	}
}
