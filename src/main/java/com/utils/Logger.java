package com.utils;


public interface Logger {

	void recordAdded(String message);
	
	void recordDeleted(String message);
	
	void validationFailure(String message);
	
	void exceptionEncountered(String message);
}
