package com.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.model.exception.DataHandlerException;

public class XmlUtils {
	/*
	 * Converts the given object type to XML string
	 */
	public static <T> String getXmlStringForObject(T object, Class<T> type) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(type);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter stringWriter = new StringWriter();
			jaxbMarshaller.marshal(object, stringWriter);
			return stringWriter.toString();

		} catch (JAXBException e) {
			throw new DataHandlerException("Error creating xml from Object for type: " + type.getName(), e);
		}
	}

	/*
	 * Converts the given xml-string to xml-Element
	 */
	public static Element getXmlNodeForString(String xmlString) {
		SAXReader reader = XmlReader.getReader();
		try {
			Document document = reader.read(new InputSource(new StringReader(xmlString)));
			return document.getRootElement();
		} catch (DocumentException e) {
			throw new DataHandlerException("Error creating xml from string: " + xmlString, e);

		}

	}
}
