package com.global.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.docs.Author;
import com.global.book.repo.AuthorRepo;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepo authorRepo;
	
	public Author findById(String id) {
	
		return authorRepo.findById(id).get();
	}

	public List<Author> findAll() {

		return authorRepo.findAll();
	}

	public Author insert(Author entity) {

		return authorRepo.insert(entity);
	}

	public List<Author> insertAll(List<Author> entity) {

		return authorRepo.saveAll(entity);
	}

	public Author update(Author entity) {
		
		Author author = findById(entity.getId());
		author.setName(entity.getName());
		author.setEmail(entity.getEmail());
		author.setPhone(entity.getPhone());
		
		return authorRepo.save(author);
	}
	
	public void deleteById(String id) {

		authorRepo.deleteById(id);
	}
	public Author findByEmail(String email) {
		return authorRepo.findByEmail(email);
	}
	public Author findByEmailProjection(String email) {
		return authorRepo.findByEmailProjection(email);
	}
	public Author findByEmailAndPhone(String email, String phone) {
		return authorRepo.findByEmailAndPhone(email, phone);
	}
	public Author updateByEmail(String email, String name, String phone) {
		return authorRepo.updateByEmail(email, name, phone);
	}
}
