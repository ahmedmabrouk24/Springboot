package com.global.book.error;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
	private Boolean success;
	private LocalDateTime time;
	private String message;
	private List<String> details;
	
	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
		this.success = false;
		this.time = LocalDateTime.now();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public Boolean getSuccess() {
		return success;
	}
	public LocalDateTime getTime() {
		return time;
	}
}
