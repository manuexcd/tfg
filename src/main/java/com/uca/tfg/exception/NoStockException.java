package com.uca.tfg.exception;

public class NoStockException extends Exception {

	private static final long serialVersionUID = -5239265396996372680L;
	
	private static final String MSG = "No stock avaible";
	
	public NoStockException() {
		super(MSG);
	}
}
