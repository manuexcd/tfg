package com.uca.tfg.exceptions;

public class NoStockException extends Exception {

	private static final long serialVersionUID = -5239265396996372680L;
	
	private static final String msg = "No stock avaible";
	
	public NoStockException() {
		super(msg);
	}
}
