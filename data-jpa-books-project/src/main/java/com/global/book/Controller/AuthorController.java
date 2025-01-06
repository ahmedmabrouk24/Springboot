package com.global.book.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.global.book.Entity.Author;
import com.global.book.Service.AuthorService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService ;
	
	@GetMapping("/id/{author_id}")
	public Author findById(@PathVariable("author_id") Long id) {
		return authorService.findById(id);
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
