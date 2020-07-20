package com.utils;

import java.util.ArrayList;
import java.util.List;

public class LoggerImpl implements Logger{

	private static List<Logger> loggers;
	private static Logger logger;

	public static Logger getLogger() {
		if(logger == null) logger = new LoggerImpl();	
		return logger;
	}

	private LoggerImpl() {
	}
	
	
	public static void registerLogger(Logger logger) {
		if(loggers == null) loggers = new ArrayList<Logger>();
		loggers.add(logger);
	}
	
	@Override
	public void recordAdded(String message) {
		loggers.forEach(logger -> logger.recordAdded(message));
		
	}

	@Override
	public void recordDeleted(String message) {
		loggers.forEach(logger -> logger.recordDeleted(message));
		
	}

	@Override
	public void validationFailure(String message) {
		loggers.forEach(logger -> logger.validationFailure(message));
		
	}

	@Override
	public void exceptionEncountered(String message) {
		loggers.forEach(logger -> logger.exceptionEncountered(message));
		
	}
	
	
}
