package com.global.book.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.global.book.Base.BaseService;
import com.global.book.Entity.Author;
import com.global.book.Error.DuplicateRecordException;
import com.global.book.Repository.AuthorRepo;
import com.global.book.Repository.AuthorSpec;

import jakarta.validation.Valid;

@Service
public class AuthorService extends BaseService<Author, Long>{
	
	@Autowired
	private AuthorRepo authorRepo;
	
	Logger log = LoggerFactory.getLogger(AuthorService.class);
	
	@Override
	public ResponseEntity<?> update(Author entity) {
		if (entity.getId() == null) {
			throw new RuntimeException();
		}
		Author updated_entity = findById(entity.getId());
		updated_entity.setName(entity.getName());
		updated_entity.setEmail(entity.getEmail());
		updated_entity.setPathImage(entity.getPathImage());
		return ResponseEntity.ok(authorRepo.save(updated_entity));
	}
	
	public List<Author> findByAuthorspec(String authorName){
		AuthorSpec authorSpec = new AuthorSpec(authorName);
		return authorRepo.findAll(authorSpec);
	}
	
	public Author findByEmail(String email) {
		return authorRepo.findByEmail(email);
	}
	
	public ResponseEntity<?> insert(@RequestBody @Valid Author author) {
		if (!author.getEmail().isEmpty() && author.getEmail() != null) { // check if the email exists
			if (findByEmail(author.getEmail()) == null) { // the email doesn't exist
				log.info("Entity Added Successfully!");
				return ResponseEntity.ok(authorRepo.save(author));
			}
			else { // the email exists
				log.error("This Email is EXIST!");
				throw new DuplicateRecordException("This Email is EXIST!");
			}
		}
		else {
			return ResponseEntity.ok(authorRepo.save(author));
		}
	}
	
}
