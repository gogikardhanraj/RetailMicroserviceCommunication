package com.databeat.exception;

public class orderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  message;
	
	public orderException(String message) {
		super();
		this.message = message;
	}
}
