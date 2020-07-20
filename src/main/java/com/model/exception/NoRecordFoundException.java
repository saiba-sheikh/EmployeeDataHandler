package com.model.exception;

public class NoRecordFoundException  extends EmployeeDataHandlerException{
	public NoRecordFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRecordFoundException(String message) {
		super(message);
	}
}
