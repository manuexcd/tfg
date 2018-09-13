package com.uca.tfg.exceptions;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -8998225839893113122L;

	private static final String MSG = "Product not found";

	public ProductNotFoundException() {
		super(MSG);
	}
}
