package com.theater.exception;

public class CannotHandlePartyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CannotHandlePartyException() {
		super("Sorry, we can't handle your party.");
	}
}
