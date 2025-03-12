package com.global.book.controller;

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

import com.global.book.docs.Author;
import com.global.book.service.AuthorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@Validated
@RestController
@RequestMapping("/api/author")
public class AuthorController {
	
	private AuthorService autherService;
	// Contractor Configuration
	public AuthorController(AuthorService autherService) {
		super();
		this.autherService = autherService;
	}

	@GetMapping("/findbyid/{id}")
	public ResponseEntity<?> findById(@PathVariable String id) {

		return ResponseEntity.ok(autherService.findById(id));
	}

	@GetMapping("/findall")
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(autherService.findAll());
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody @Valid Author entity) {

		return ResponseEntity.ok(autherService.insert(entity));
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody @Valid Author entity) {

		return ResponseEntity.ok(autherService.update(entity));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable String id) {
		autherService.deleteById(id);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/findbyemail/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable @Email String email) {
		return ResponseEntity.ok(autherService.findByEmail(email));
	}
	
	@GetMapping("/findbyemailpro/{email}")
	public ResponseEntity<?> findByEmailProjection(@PathVariable String email) {
		return ResponseEntity.ok(autherService.findByEmailProjection(email));
	}
	
	@GetMapping("/findbyemailandphone/{email}/{phone}")
	public ResponseEntity<?> findByEmailAndPhone(@PathVariable String email, @PathVariable String phone) {
		return ResponseEntity.ok(autherService.findByEmailAndPhone(email, phone));
	}
	
	@PutMapping("/updatebyemail/{email}/{name}/{phone}")
	public Author updateByEmail(@PathVariable String email, @PathVariable String name, @PathVariable String phone) {
		return autherService.updateByEmail(email, name, phone);
	}
}
