package com.helper;

import java.util.HashMap;

import com.model.exception.DataHandlerException;

public class DataHandlingFactory {
	
	private static HashMap<Class<?>,DataHandler<?> > dataHandlerRegistry = new HashMap<Class<?>, DataHandler<?>>();
	
	/*
	 * Registers the different types of handlers supported in the application
	 */
	public static void register(Class<?> classType, DataHandler<?> handler) {
		dataHandlerRegistry.put(classType, handler);
	}
	/*
	 * The factory return dataHandler reference for the provided database type and object
	 */
	public static<T> T getDataHandler(String dbType, Class objectType, String source) { 
		if(dbType.equalsIgnoreCase("XML")) 
			return getInstanceOfXmlHandler(objectType, source);
		else
			throw new DataHandlerException("Unsupported DB type : "+ dbType );
	}
	/*
	 * Creates instance of XmlHandler
	 */
	protected static <T> T getInstanceOfXmlHandler(Class objectType, String source) {
			if( dataHandlerRegistry.containsKey(objectType) )
				return (T)dataHandlerRegistry.get(objectType);
			else 
				throw new DataHandlerException("Unsupported handler for type : "+ objectType.getName() );

	}
}
