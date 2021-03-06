package com.sun.app.exception;

public class AuthenticationException extends RuntimeException{

	private int errorCode;
	private String message;
	
	public AuthenticationException(int errorCode, String message){
		this.errorCode = errorCode;
		this.message = message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
