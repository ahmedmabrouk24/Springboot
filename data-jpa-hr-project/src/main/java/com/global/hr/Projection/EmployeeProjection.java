package com.global.hr.Projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
	// closed projection
	Long getId();
	String getFirstName();
	String getLastName();
	
	// open projection
	@Value("#{target.firstName + ' ' + target.lastName}")
	String getFullName();
	
}
