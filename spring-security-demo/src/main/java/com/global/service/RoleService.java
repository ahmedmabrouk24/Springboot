package com.global.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.global.entity.Role;
import com.global.repository.RoleRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	private final RoleRepo roleRepo;
	
	public Role findByName(String name){
		return roleRepo.findByName(name);
	}
	
	public List<Role> findAll(){
		return roleRepo.findAll();
	}
	
	public Role findById(Long id){
		return roleRepo.findById(id).orElse(null);
	}
	
	public Role insert(Role user){
		return roleRepo.save(user);
	}
}
