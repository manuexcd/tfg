package com.uca.tfg.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -4328489130495899086L;

	private static final String msg = "User not found";

	public UserNotFoundException() {
		super(msg);
	}
}
