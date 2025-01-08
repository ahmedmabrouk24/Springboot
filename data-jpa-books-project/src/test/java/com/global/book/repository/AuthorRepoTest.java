package com.global.book.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.global.book.entity.Author;

@SpringBootTest
public class AuthorRepoTest {
	@Autowired
	AuthorRepo authorRepo;
	
	@Test
	void findByEmailNotFoundTest() {
		Optional<Author> author = authorRepo.findByEmail("XXX@gmail.com");
		assertEquals(false, author.isPresent());
	}
	
	@Test
	void findByEmailFoundTest() {
		Optional<Author> author = authorRepo.findByEmail("ahmed@gmail.com");
		assertEquals("ahmed@gmail.com", author.get().getEmail());
	}
}
