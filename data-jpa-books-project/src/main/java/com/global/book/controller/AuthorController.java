package com.global.book.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.dto.AuthorDto;
import com.global.book.entity.Author;
import com.global.book.mapper.AuthorMapper;
import com.global.book.service.AuthorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
	
	private final AuthorService authorService ;
	
	private final AuthorMapper authorMapper;
	
	@GetMapping("/id/{author_id}")
	public AuthorDto findById(@PathVariable("author_id") Long id) {
		Author author = authorService.findById(id);
		
		AuthorDto authorDto = authorMapper.mapToDto(author);
		
		return authorDto;
	}
	@GetMapping("/name/{author_name}")
	public Author findByName(@PathVariable("author_name") String name) {
		return authorService.findByName(name);
	}
	@GetMapping("/findall")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(authorService.findAll());
	}
	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok(authorService.findByEmail(email));
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody @Valid Author author) {
		return ResponseEntity.ok(authorService.insert(author));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody @Valid Author author) {
		return ResponseEntity.ok(authorService.update(author));
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		authorService.delete(id);	
	}
	@GetMapping("/findallspec/{authorName}")
	public List<Author> findByAuthorspec(@PathVariable String authorName){
		return authorService.findByAuthorspec(authorName);
	}
}
