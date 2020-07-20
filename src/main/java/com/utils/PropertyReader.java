package com.utils;

import java.io.InputStream;
import java.util.Properties;

import com.model.exception.ReaderException;

/*
 * Reads the property defined for the class
 */
public class PropertyReader {

	private static PropertyReader reader = null;
	static Properties prop = null;
	static InputStream in = null;

	private PropertyReader() {
		try {
			in = this.getClass().getResourceAsStream("/config.properties");
			prop = new Properties();
			prop.load(in);
		}

		catch (Exception e) {
			throw new ReaderException("Unable to read properties", e);
		}
	}

	public static PropertyReader getInstance() throws ReaderException {
		if (reader == null) {
			reader = new PropertyReader();
		}
		return reader;
	}

	public String getProperty(String property) {
		return prop.getProperty(property);
	}
}
