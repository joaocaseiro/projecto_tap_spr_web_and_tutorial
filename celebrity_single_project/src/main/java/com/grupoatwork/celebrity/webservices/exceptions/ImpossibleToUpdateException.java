package com.grupoatwork.celebrity.webservices.exceptions;

public class ImpossibleToUpdateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ImpossibleToUpdateException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + " - " + message;
	}
}
