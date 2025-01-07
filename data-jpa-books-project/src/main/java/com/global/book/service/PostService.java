package com.global.book.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.global.book.dto.PostDto;

@Service
public class PostService {
	
	public static String basePath = "https://jsonplaceholder.typicode.com/posts";
	
	public PostDto getPostById(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PostDto> post = restTemplate.getForEntity(basePath + "/" + id, PostDto.class);
		return post.getBody();
	}
	
	public List<PostDto> getAllPosts() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List> post = restTemplate.getForEntity(basePath, List.class);
		return post.getBody();
	}
	
	public PostDto addPost(PostDto post) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<PostDto> httpEntity = new HttpEntity<>(post);
		ResponseEntity<PostDto> responseEntity = restTemplate.postForEntity(basePath, httpEntity, PostDto.class);
		return responseEntity.getBody();
	}
	
	public void updatePost(PostDto post) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity httpEntity = new HttpEntity<>(post);
		restTemplate.put(basePath, httpEntity);
	}
	
	public void deletePost(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(basePath + '/' + id);
	}
}
