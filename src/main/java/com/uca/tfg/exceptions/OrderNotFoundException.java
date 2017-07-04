package com.uca.tfg.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = -5128791542128687323L;

	private static final String msg = "Order not found";

	public OrderNotFoundException() {
		super(msg);
	}
}
