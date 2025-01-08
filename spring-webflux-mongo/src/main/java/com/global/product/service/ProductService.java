package com.global.product.service;

import org.springframework.stereotype.Service;

import com.global.product.entity.Product;
import com.global.product.repository.ProductRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	public Mono<Product> findById(long id){
		return productRepository.findById(id);
	}
	
	public Flux<Product> findByName(String name){
		return productRepository.findByName(name);
	}
	
	public Flux<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Mono<Product> insert(Product product){
		return productRepository.insert(product);
	}
	
	public Mono<Product> update(Product product){
		return productRepository.save(product);
	}
	
	public Mono<Void> deleteById(long id){
		return productRepository.deleteById(id);
	}
}
