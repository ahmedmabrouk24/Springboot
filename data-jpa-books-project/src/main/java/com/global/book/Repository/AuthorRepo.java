package com.global.book.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.global.book.Base.BaseRepository;
import com.global.book.Entity.Author;

@Repository
public interface AuthorRepo extends BaseRepository<Author, Long>, JpaSpecificationExecutor<Author>{
	
}
