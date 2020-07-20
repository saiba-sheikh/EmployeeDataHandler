package com.utils;
/*
 * Logs messages in console
 */
public class ConsoleLogger implements Logger {
		
	@Override
	public void recordAdded(String message) {
		printToConsole(message);
		
	}

	@Override
	public void recordDeleted(String message) {
		printToConsole(message);
		
	}

	@Override
	public void validationFailure(String message) {
		printToConsole(message);		
	}

	@Override
	public void exceptionEncountered(String message) {
		printToConsole(message);		
	}

	private void printToConsole(String message) {
		System.out.println(message);
	}

}
