package com.global.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.global.book.dto.PostDto;
import com.global.book.service.PostService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/post")
public class PostController {
	@Autowired
	PostService postService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getPostById(@PathVariable Long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@GetMapping("/findall")
	public ResponseEntity<?> getAllPosts() {
		return ResponseEntity.ok(postService.getAllPosts());
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addPost(@RequestBody PostDto post) {
		return ResponseEntity.ok(postService.addPost(post));
	}
	
	@PutMapping("/update")
	public void updatePost(@RequestBody PostDto post) {
		postService.updatePost(post);
	}
	
	@DeleteMapping("/delete")
	public void deletePost(Long id) {
		postService.deletePost(id);
	}
}
