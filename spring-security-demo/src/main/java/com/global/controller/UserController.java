package com.global.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.entity.AppUser;
import com.global.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {
	private final UserService userService;
	
	@GetMapping("/findall")
	public List<AppUser> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/id/{id}")
	public AppUser findById(@PathVariable Long id){
		return userService.findById(id);
	}
	
	@PostMapping("/insert")
	public AppUser insert(@RequestBody AppUser user){
		return userService.insert(user);
	}
}
