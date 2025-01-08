package com.global.book.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.global.book.base.BaseService;
import com.global.book.entity.Author;
import com.global.book.error.DuplicateRecordException;
import com.global.book.repository.AuthorRepo;
import com.global.book.repository.AuthorSpec;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorService extends BaseService<Author, Long>{
	
	private final AuthorRepo authorRepo;
	
	@Override
	@Cacheable(value = "finaAllAuthors", key = "#root.methodName")
	public List<Author> findAll() {
		return super.findAll();
	}
	
	@Override
	@Cacheable(value = "finaAuthorById", key = "#id")
	public Author findById(Long id) {
		// TODO Auto-generated method stub
		return super.findById(id);
	}
	
	@Override
	@CacheEvict(value = {"finaAllAuthors", "finaAuthorById"}, key = "#root.methodName", allEntries = true)
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
	
	public Optional<Author> findByEmail(String email) {
		return authorRepo.findByEmail(email);
	}
	
	@CacheEvict(value = {"finaAllAuthors", "finaAuthorById"}, key = "#root.methodName", allEntries = true)
	public Author insert(@RequestBody @Valid Author author) {
		if (!author.getEmail().isEmpty() && author.getEmail() != null) { // check if the email exists
			if (findByEmail(author.getEmail()) == null) { // the email doesn't exist
				log.info("Entity Added Successfully!");
				return authorRepo.save(author);
			}
			else { // the email exists
				log.error("This Email is EXIST!");
				throw new DuplicateRecordException("This Email is EXIST!");
			}
		}
		else {
			return authorRepo.save(author);
		}
	}
	
	
}
