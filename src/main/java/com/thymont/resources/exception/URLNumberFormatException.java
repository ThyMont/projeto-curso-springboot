package com.thymont.resources.exception;

public class URLNumberFormatException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public URLNumberFormatException(String msg) {
		super(msg);
	}
	
	public URLNumberFormatException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
