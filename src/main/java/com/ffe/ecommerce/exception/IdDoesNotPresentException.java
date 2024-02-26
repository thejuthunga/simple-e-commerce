package com.ffe.ecommerce.exception;

public class IdDoesNotPresentException extends RuntimeException{
	
	private String message="Id is Not Present";

	public IdDoesNotPresentException() {
		
	}

	public IdDoesNotPresentException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
