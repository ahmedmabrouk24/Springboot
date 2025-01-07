package com.global.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.book.entity.Book;
import com.global.book.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService ;
	
	@GetMapping("/id/{book_id}")
	public Book findById(@PathVariable("book_id") Long id) {
		return bookService.findById(id);
	}
	@GetMapping("/name/{book_name}")
	public Book findByName(@PathVariable("book_name") String name) {
		return bookService.findByName(name);
	}
	@GetMapping("/findall")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(bookService.findAll());
	}
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody @Valid Book book) {
		return ResponseEntity.ok(bookService.insert(book));
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody @Valid Book book) {
		return ResponseEntity.ok(bookService.update(book));
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		bookService.delete(id);	
	}
}
