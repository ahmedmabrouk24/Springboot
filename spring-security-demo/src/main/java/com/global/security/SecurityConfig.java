package com.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	private final JwtUnAuthResponse jwtUnAuthResponse;

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
		return auth.build();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(authorizeRequests -> 
			authorizeRequests
				.requestMatchers("/login", "/api/v1/auth/login").permitAll() // Allow
				.requestMatchers("/user/**").hasAuthority("user")
				.requestMatchers("/role/**").hasAuthority("admin")
				.anyRequest().authenticated() // Other paths require authentication
			)
		.formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
		.formLogin(formLogin -> formLogin.defaultSuccessUrl("/home/user/playPage"))
		.logout(logout -> 
			logout
				.permitAll() // Allow access to logout functionality
			)
		.httpBasic(httpBasic -> 
			httpBasic
				.authenticationEntryPoint(jwtUnAuthResponse) // If Token is missed or wrong
			)
		.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);

	return http.build();
	}
	
	@Bean
	AuthFilter authFilter() {
		return new AuthFilter();
	}
}
