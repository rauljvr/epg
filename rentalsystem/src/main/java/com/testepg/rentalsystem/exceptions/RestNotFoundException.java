package com.testepg.rentalsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestNotFoundException extends RestException {

	private static final long serialVersionUID = 1L;

	public RestNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestNotFoundException(String message) {
		super(message);
	}

	public RestNotFoundException(Throwable cause) {
		super(cause);
	}

}
