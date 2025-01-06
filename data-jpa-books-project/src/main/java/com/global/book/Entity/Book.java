package com.global.book.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.global.book.Base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "books")
public class Book extends BaseEntity<Long>{
	@NotNull(message = "Enter Book Name!")
	private String name;
	
	@Min(5)
	@Max(5000)
	private Double price;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Author author;
	
	private String bookIcon;
	
	public String getBookIcon() {
		return bookIcon;
	}

	public void setBookIcon(String bookIcon) {
		this.bookIcon = bookIcon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
}
