package com.utils;

import org.dom4j.io.SAXReader;
/*
 * Gives singleton instance of SAXReader
 */
public class XmlReader extends SAXReader {
	private static XmlReader reader;
	private XmlReader() {
	}

	public static XmlReader getReader() {
		if (reader == null) {
			reader = new XmlReader();
		}
		return reader; 
	}
}
