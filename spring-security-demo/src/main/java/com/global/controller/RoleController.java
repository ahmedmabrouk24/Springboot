package com.global.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.entity.Role;
import com.global.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
	private final RoleService roleService;
	
	@GetMapping("/findbyname/{name}")
	public Role findByName(@PathVariable String name){
		return roleService.findByName(name);
	}
	
	@GetMapping("/findall")
	public List<Role> findAll(){
		return roleService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public Role findById(@PathVariable Long id){
		return roleService.findById(id);
	}
	
	@PostMapping("/insert")
	public Role insert(@RequestBody Role user){
		return roleService.insert(user);
	}
}
