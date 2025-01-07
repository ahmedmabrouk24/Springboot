package com.global.book.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.global.book.base.BaseService;
import com.global.book.entity.Book;

@Service
public class BookService extends BaseService<Book, Long>{
	@Override
	public ResponseEntity<?> update(Book entity) {
		if (entity.getId() == null) {
			throw new RuntimeException();
		}
		Book updated_entity = findById(entity.getId());
		updated_entity.setName(entity.getName());
		return ResponseEntity.ok(update(updated_entity));
	}
}
