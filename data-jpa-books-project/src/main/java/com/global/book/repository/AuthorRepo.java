package com.global.book.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.global.book.base.BaseRepository;
import com.global.book.entity.Author;

@Repository
public interface AuthorRepo extends BaseRepository<Author, Long>, JpaSpecificationExecutor<Author>{
	Author findByEmail(String email);
}