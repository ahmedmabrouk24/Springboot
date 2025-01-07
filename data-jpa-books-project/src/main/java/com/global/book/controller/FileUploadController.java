package com.global.book.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.global.book.service.FileUplaodService;

@RestController
@RequestMapping("/file")
public class FileUploadController {

	@Autowired
	private FileUplaodService fileUplaodService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam Long id,@RequestParam String pathType,
			@RequestParam MultipartFile multipartFile){
		File file = fileUplaodService.convertMultiPartFileToFile(multipartFile);
		
		String fileName = fileUplaodService.storeFile(file, id, pathType);
		return ResponseEntity.ok(fileName);
	}
}
