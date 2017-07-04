package com.uca.tfg.exceptions;

public class OrderLineNotFoundException extends Exception {

	private static final long serialVersionUID = 6762482809584858763L;

	private static final String msg = "No order line found";

	public OrderLineNotFoundException() {
		super(msg);
	}
}
