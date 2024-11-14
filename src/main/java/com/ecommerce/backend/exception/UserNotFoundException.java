package com.ecommerce.backend.exception;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(Long id) {
		super("Could not found id" +id);
	}

}
