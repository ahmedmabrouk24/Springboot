package com.global.book.entity;

import com.global.book.base.BaseEntity;
import com.global.book.validitor.ipAddress;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity<Long>{
	
	@NotNull
	private String name;
	
	//@Pattern(regexp = "^([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})$")
	@ipAddress()
	private String ipAddress;
	
	@Email()
	private String email;
	
	private String pathImage;
	
}
