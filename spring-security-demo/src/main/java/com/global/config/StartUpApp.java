package com.global.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.entity.AppUser;
import com.global.entity.Role;
import com.global.service.RoleService;
import com.global.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartUpApp implements CommandLineRunner {

	private final UserService userService;
	private final RoleService roleService;

	@Override
	public void run(String... args) throws Exception {
		
		if (roleService.findAll().isEmpty()) {
			Role admin = new Role();
			admin.setName("admin");
			roleService.insert(admin);
			
			Role user = new Role();
			user.setName("user");
			roleService.insert(user);
			
			Role employee = new Role();
			employee.setName("employee");
			roleService.insert(employee);
		}
		
		if (userService.findAll().isEmpty()) {
			
			Set<Role> adminRole = new HashSet<>();
			adminRole.add(roleService.findByName("admin"));
			AppUser admin = new AppUser();
			admin.setFullName("ahmed mabrouk");
			admin.setUserName("ahmed@gmail.com");
			admin.setPassword("123");
			admin.setRoles(adminRole);
			userService.insert(admin);
			
			Set<Role> userRole = new HashSet<>();
			userRole.add(roleService.findByName("user"));
			AppUser user = new AppUser();
			user.setFullName("mohamed mabrouk");
			user.setUserName("mohamed@gmail.com");
			user.setPassword("123");
			user.setRoles(userRole);
			userService.insert(user);
			
			Set<Role> employeeRole = new HashSet<>();
			employeeRole.add(roleService.findByName("employee"));
			AppUser employee = new AppUser();
			employee.setFullName("ali kamal");
			employee.setUserName("ali@gmail.com");
			employee.setPassword("123");
			employee.setRoles(employeeRole);
			userService.insert(employee);
		}
	}

}
