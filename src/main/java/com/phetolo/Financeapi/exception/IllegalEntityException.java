package com.phetolo.Financeapi.exception;

@SuppressWarnings("serial")
public class IllegalEntityException extends RuntimeException{
	public IllegalEntityException(String message) {
		super(message);
	}
}
