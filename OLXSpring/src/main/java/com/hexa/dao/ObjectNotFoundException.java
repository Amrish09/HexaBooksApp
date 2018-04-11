package com.hexa.dao;

/**
 * @author Priya
 * @author Bijitha
 * @version 1.0
 * <p>contains ObjectNotFoundException</p>
 */
public class ObjectNotFoundException extends Exception {

public ObjectNotFoundException(String message, Throwable cause) {
	super(message, cause);
}

public ObjectNotFoundException(String message) {
	super(message);
}
}
