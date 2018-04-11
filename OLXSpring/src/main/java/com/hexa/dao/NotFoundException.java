package com.hexa.dao;

/**
 * @author Priya
 * @author Bijitha
 * @version 1.0
 * <p>contains NotFoundException</p>
 */
public class NotFoundException extends Exception{

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}
}
