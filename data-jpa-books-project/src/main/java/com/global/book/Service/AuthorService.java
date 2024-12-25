package com.global.book.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.global.book.Base.BaseService;
import com.global.book.Entity.Author;

@Service
public class AuthorService extends BaseService<Author, Long>{
	@Override
	public ResponseEntity<?> update(Author entity) {
		if (entity.getId() == null) {
			throw new RuntimeException();
		}
		Author updated_entity = findById(entity.getId());
		updated_entity.setName(entity.getName());
		return ResponseEntity.ok(update(updated_entity));
	}
}
