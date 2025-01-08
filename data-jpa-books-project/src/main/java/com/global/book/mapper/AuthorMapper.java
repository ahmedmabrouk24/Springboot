package com.global.book.mapper;

import org.mapstruct.Mapper;

import com.global.book.dto.AuthorDto;
import com.global.book.entity.Author;

@Mapper
public interface AuthorMapper {

	//@Mapping(source = "address", target = "ipAddress")
	Author mapToEntity(AuthorDto authorDto);
	
	AuthorDto mapToDto(Author author);
}
