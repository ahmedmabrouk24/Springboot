package com.global.Bosta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.Bosta.model.UserModel;
import com.global.Bosta.service.UserService;

@RestController
@RequestMapping("/api/v2")
public class UsersController {
	private final UserService userService;
	
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody UserModel request) {
        return userService.createUserAccount(request);
    }
}
