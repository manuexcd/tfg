package com.uca.tfg.exceptions;

public class EmailExistsException extends Exception {
	
	private static final long serialVersionUID = 7319980745274300196L;

	public EmailExistsException(String msg) {
		super(msg);
	}
}
