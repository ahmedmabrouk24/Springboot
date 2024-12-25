package com.global.book.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		// should get userName from Spring Boot security
		return Optional.of("tester user");
	}

}
