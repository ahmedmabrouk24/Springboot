package com.global.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Book;

@Repository
public interface BookRepo extends BaseRepository<Book, Long>{
	public Book findByName(String name);
	
	@Override
	@EntityGraph(attributePaths = {"author"})
	Optional<Book> findById(Long id);
}
