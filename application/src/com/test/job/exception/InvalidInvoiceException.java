package com.test.job.exception;

public class InvalidInvoiceException extends Exception {
	
	private static final long serialVersionUID = 1997753L;

	public InvalidInvoiceException(String message){
		super(message);
	}
}
