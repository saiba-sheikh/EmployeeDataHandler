package com.model.exception;

public abstract class EmployeeDataHandlerException extends RuntimeException {
	public EmployeeDataHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeDataHandlerException(String message) {
		super(message);
	}
}
