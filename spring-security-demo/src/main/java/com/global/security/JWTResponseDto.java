package com.global.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JWTResponseDto {
	
	public String accessToken;
	
	public String refreshToken;
	
}