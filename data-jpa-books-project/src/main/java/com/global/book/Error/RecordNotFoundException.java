package com.global.book.Error;

public class RecordNotFoundException extends RuntimeException {

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message) {
		super(message);
	}
	
}
