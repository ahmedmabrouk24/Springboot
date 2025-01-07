package com.global.book.error;

public class DuplicateRecordException extends RuntimeException{

	public DuplicateRecordException() {
		super();
	}
	public DuplicateRecordException(String message) {
		super(message);
	}
	
}
