package com.phetolo.Financeapi.exception;

@SuppressWarnings("serial")
public class IllegalTransactionException extends Exception {
	public IllegalTransactionException(String message) {
		super(message);
	}
}
