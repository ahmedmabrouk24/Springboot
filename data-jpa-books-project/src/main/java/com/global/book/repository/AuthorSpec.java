package com.global.book.repository;

import org.springframework.data.jpa.domain.Specification;

import com.global.book.entity.Author;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AuthorSpec implements Specification<Author>{

	private String authorName;
	
	public AuthorSpec(String authorName) {
		super();
		this.authorName = authorName;
	}
	@Override
	public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		return criteriaBuilder.like(root.get("name"), authorName);
	}
	

}
