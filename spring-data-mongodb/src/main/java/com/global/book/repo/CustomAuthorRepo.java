package com.global.book.repo;

import com.global.book.docs.Author;

public interface CustomAuthorRepo {
	Author updateByEmail(String email, String name, String phone);
}
