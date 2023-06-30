package com.cybersoft.cozastore_java21.exception;

@SuppressWarnings("serial")
public class CustomFileNotFoundException extends RuntimeException{
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CustomFileNotFoundException(String message) {
		this.message = message;
	}
	public CustomFileNotFoundException() {
	}
	
	
}
