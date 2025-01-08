package com.global.product.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.product.entity.Product;
import com.global.product.service.ProductService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

	private final ProductService productService;

	@GetMapping(value =  "/id-reactive/{id}", produces = "text/event-stream")
	public Mono<Product> findById(@PathVariable long id){
		return productService.findById(id);
	}
	
	@GetMapping(value =  "/name-reactive/{name}", produces = "text/event-stream")
	public Flux<Product> findByName(@PathVariable String name){
		return productService.findByName(name);
	}
	
	@GetMapping(value =  "/all-reactive", produces = "text/event-stream")
	public Flux<Product> findAll(){
		return productService.findAll();
	}
	
	@PostMapping(value =  "/insert-reactive", produces = "text/event-stream")
	public Mono<Product> insert(@RequestBody Product product){
		return productService.insert(product);
	}
	
	@PutMapping(value =  "/update-reactive", produces = "text/event-stream")
	public Mono<Product> update(@RequestBody Product product){
		return productService.update(product);
	}
	
	@DeleteMapping(value =  "/delete-reactive/{id}", produces = "text/event-stream")
	public Mono<Void> deleteById(@PathVariable long id){
		return productService.deleteById(id);
	}
}
