package com.global.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto<ID> {
	ID id;
	private String statusCode;
	private boolean isDeleted;
}
