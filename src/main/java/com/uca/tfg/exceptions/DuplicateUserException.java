package com.uca.tfg.exceptions;

public class DuplicateUserException extends Exception {
	
	private static final long serialVersionUID = 7319980745274300196L;

	private static final String msg = "Duplicate user";

	public DuplicateUserException() {
		super(msg);
	}
}
