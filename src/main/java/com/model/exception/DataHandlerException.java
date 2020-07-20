package com.model.exception;

public class DataHandlerException extends EmployeeDataHandlerException{
	public DataHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataHandlerException(String message) {
		super(message);
	}
}
