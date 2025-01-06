package com.global.book.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.book.Entity.Author;
import com.global.book.Entity.Book;
import com.global.book.Service.AuthorService;
import com.global.book.Service.BookService;

@Component
public class StartUpApp implements CommandLineRunner{

	@Autowired
	private AuthorService autherService;

	@Autowired
	private BookService bookService;
	
	@Override
	public void run(String... args) throws Exception {
		// adding some data for authors
		if (autherService.findAll().isEmpty()) {
			Author auther1 = new Author();
			auther1.setName("ALi");
	
			Author auther2 = new Author();
			auther2.setName("Mohamed");
	
			Author auther3 = new Author();
			auther3.setName("Ahmed");
	
			autherService.insertAll(Arrays.asList(auther1, auther2, auther3));
		}
		// adding some data for books
		if(bookService.findAll().isEmpty()) {
			Book book = new Book();
			book.setName("Java JPA");
			book.setPrice((double) 200);
			book.setAuthor(autherService.findById(1L));
	
			Book book2 = new Book();
			book2.setName("Data Base Mysql");
			book2.setPrice((double) 300);
			book2.setAuthor(autherService.findById(1L));
	
			Book book3 = new Book();
			book3.setName("Python");
			book3.setPrice((double) 120);
			book3.setAuthor(autherService.findById(2L));
	
	
			bookService.insertAll(Arrays.asList(book,book2, book3));
		}
		
	}

}
