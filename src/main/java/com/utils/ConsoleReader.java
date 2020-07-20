package com.utils;

import java.util.Scanner;
/*
 * Gives singleton instance of Scanner
 */
public final class ConsoleReader {

	private Scanner reader;
	private static ConsoleReader consoleReader;
	private ConsoleReader() {
		reader = new Scanner(System.in);
	}
	
	public static ConsoleReader getConsoleReader() {
		if(consoleReader == null)
			consoleReader = new ConsoleReader();
		return consoleReader;
	}
	
	public Scanner reader() {
		return reader;
	}
	
	public void closeReader(){
	reader.close();
	}
}
