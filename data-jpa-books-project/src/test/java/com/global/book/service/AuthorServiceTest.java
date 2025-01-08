package com.global.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.global.book.entity.Author;
import com.global.book.repository.AuthorRepo;

@SpringBootTest
public class AuthorServiceTest {
	@Autowired
	AuthorService authorService;
	
	@MockitoBean
	AuthorRepo authorRepo;
	
	@Test
	void findByEmailNotFoundTest() {
		Optional<Author> author = Optional.empty();
		Mockito.when(authorRepo.findByEmail(anyString())).thenReturn(author);
		Optional<Author> returnedAuthor = authorService.findByEmail("ahmed@gmail.com");
		assertEquals(false, returnedAuthor.isPresent());
	}
	
	@Test
	void findByEmailFoundTest() {
		Optional<Author> author = Optional.of(new Author("ahmed", "192.168.1.1", "ahmed@gmail.com", null));
		Mockito.when(authorRepo.findByEmail(anyString())).thenReturn(author);
		Optional<Author> returnedAuthor = authorService.findByEmail("ahmed@gmail.com");
		assertEquals(true, returnedAuthor.isPresent());
		assertEquals("ahmed@gmail.com", returnedAuthor.get().getEmail());
	}
}
