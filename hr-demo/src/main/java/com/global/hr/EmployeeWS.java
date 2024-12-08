package com.global.hr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeWS {

	@GetMapping("/name")
	public String getEmpName(){
		return "Employee name";
	}
}
