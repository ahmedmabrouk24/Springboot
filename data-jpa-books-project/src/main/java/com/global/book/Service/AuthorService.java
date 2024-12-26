package com.global.book.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.global.book.Base.BaseService;
import com.global.book.Entity.Author;
import com.global.book.Repository.AuthorRepo;
import com.global.book.Repository.AuthorSpec;

@Service
public class AuthorService extends BaseService<Author, Long>{
	
	@Autowired
	private AuthorRepo authorRepo;
	@Override
	public ResponseEntity<?> update(Author entity) {
		if (entity.getId() == null) {
			throw new RuntimeException();
		}
		Author updated_entity = findById(entity.getId());
		updated_entity.setName(entity.getName());
		return ResponseEntity.ok(update(updated_entity));
	}
	
	public List<Author> findByAuthorspec(String authorName){
		AuthorSpec authorSpec = new AuthorSpec(authorName);
		return authorRepo.findAll(authorSpec);
	}
}
