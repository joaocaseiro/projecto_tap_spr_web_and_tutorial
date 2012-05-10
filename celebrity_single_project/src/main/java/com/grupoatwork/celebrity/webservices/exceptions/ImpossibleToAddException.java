package com.grupoatwork.celebrity.webservices.exceptions;

public class ImpossibleToAddException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ImpossibleToAddException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + " - " + message;
	}
}
