package com.uca.tfg.exception;

public class ImageNotFoundException extends Exception {

	private static final long serialVersionUID = -4328489130495899086L;

	private static final String MSG = "Image not found";

	public ImageNotFoundException() {
		super(MSG);
	}
}
