package com.global.book.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;

import java.util.Optional;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.global.book.dto.AuthorDto;
import com.global.book.entity.Author;
import com.global.book.service.AuthorService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {

	@Autowired
	AuthorController authorController;

	@Autowired
	TestRestTemplate testRestTemplate;

	@MockitoBean
	AuthorService authorService;

	@Test
	void findByEmailNotFoundTest() {
		Mockito.when(authorService.findByEmail(anyString())).thenReturn(Optional.empty());
		String email = "xxx@gmail.com";
		ResponseEntity<AuthorDto> returnedAuthor = testRestTemplate.getForEntity("/author/findbyemail/" + email,
				AuthorDto.class);
		assertThat(returnedAuthor.getBody()).isNull();
	}

	@Test
	void findByEmailFoundTest() {
		Optional<Author> author = Optional.of(new Author("ahmed", "192.168.1.1", "ahmed@gmail.com", null));
		Mockito.when(authorService.findByEmail(anyString())).thenReturn(author);
		String email = "ahmed@gmail.com";
		ResponseEntity<AuthorDto> returnedAuthor = testRestTemplate.getForEntity("/author/findbyemail/" + email,
				AuthorDto.class);
		assertThat(returnedAuthor.getBody().getEmail()).isEqualTo("ahmed@gmail.com");
	}

	@Test
	void insertAuthorTest() {

		Author author = new Author("ahmed", "192.168.1.1", "ahmed@gmail.com", null);
		Mockito.when(authorService.insert(Mockito.any(Author.class))).thenReturn(author);
		
		ResponseEntity<Author> response = testRestTemplate
				.postForEntity("/author/insert", author, Author.class);
		
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getName()).isEqualTo("ahmed");
		assertThat(response.getBody().getIpAddress()).isEqualTo("192.168.1.1");
		assertThat(response.getBody().getEmail()).isEqualTo("ahmed@gmail.com");
	}
}
