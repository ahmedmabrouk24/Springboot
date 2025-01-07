package com.global.book.dto;

import com.global.book.validitor.ipAddress;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto extends BaseDto<Long>{
	
	@NotNull
	private String name;
	
	@ipAddress()
	private String ipAddress;
	
	@Email()
	private String email;

}
