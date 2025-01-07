package com.global.book.dto;

import com.global.book.entity.Author;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto<Long>{
	
	@NotNull(message = "Enter Book Name!")
	private String name;
	
	@Min(5)
	@Max(5000)
	private Double price;
	
	private AuthorDto author;
	
	private String bookIcon;
}
