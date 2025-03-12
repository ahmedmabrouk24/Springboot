package com.global.book.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.book.docs.Author;

@Repository
public interface AuthorRepo extends MongoRepository<Author, String>, CustomAuthorRepo{
	@Query(value = "{email : '?0'}")
	public Author findByEmail(String e);
	
	@Query(value = "{email : '?0'}", fields = "{'name': 1,'email': 1}")
	public Author findByEmailProjection(String e);
	
	@Query(value = "{email : '?0', phone: '?1'}")
	public Author findByEmailAndPhone(String email, String phone);
}
