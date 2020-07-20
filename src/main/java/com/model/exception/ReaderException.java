package com.model.exception;

public class ReaderException extends EmployeeDataHandlerException {

	public ReaderException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReaderException(String message) {
		super(message);
	}

}
