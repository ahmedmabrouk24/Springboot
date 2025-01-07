package com.global.book.mapper;

import org.mapstruct.Mapper;

import com.global.book.dto.BookDto;
import com.global.book.entity.Book;

@Mapper
public interface BookMapper {

	Book mapToEntity(BookDto bookDto);
	
	BookDto mapToDto(Book author);
}
