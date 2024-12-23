package com.global.hr.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.hr.Entity.Role;
import com.global.hr.Entity.User;
import com.global.hr.Servise.RoleServise;
import com.global.hr.Servise.UserServise;

@Component
public class AppStratUp implements CommandLineRunner{

	@Autowired
	private RoleServise roleServise;
	@Autowired
	private UserServise userServise;
	@Override
	public void run(String... args) throws Exception {
		if(userServise.findAll().isEmpty() == true) {
			// create roles 
			Role role1 = new Role();
			role1.setRoleName("Admin");
			Role role2 = new Role();
			role2.setRoleName("User");
			
			roleServise.insert(role1);
			roleServise.insert(role2);
			
			Set<Role> adminRole = new HashSet<>();
			adminRole.add(role1);
			
			Set<Role> userRole = new HashSet<>();
			userRole.add(role2);
			
			// create users
			User user1 = new User();
			user1.setUserName("user1");
			user1.setPassword("123");
			user1.setRole(adminRole);
			
			User user2 = new User();
			user2.setUserName("user2");
			user2.setPassword("123");
			user2.setRole(userRole);
			
			userServise.insert(user1);
			userServise.insert(user2);
		}
		
	}

}
