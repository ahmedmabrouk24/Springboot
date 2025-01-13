package com.global.security;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtUnAuthResponse implements AuthenticationEntryPoint , Serializable  {

	private static final long serialVersionUID = 2848589597094595376L;
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		authException.printStackTrace();
		final String authExceptionpired = (String) request.getAttribute("authExceptionpired");
		System.out.println("authException >>>>" + authExceptionpired);
		
		if (authExceptionpired!=null){
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authExceptionpired);
        }
		else{
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED
        			, "You would need to provide the Jwt token to access this resource");
        }
		
	}

}