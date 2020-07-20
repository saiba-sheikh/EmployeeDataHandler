package com.helper;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import com.model.exception.DataHandlerException;
import com.model.exception.NoRecordFoundException;
import com.model.exception.ReaderException;
import com.utils.Logger;
import com.utils.LoggerImpl;
import com.utils.PropertyReader;
import com.utils.XmlReader;
import com.utils.XmlUtils;

public class XmlDataHandler<U> implements DataHandler<U> {
	private static final String query = PropertyReader.getInstance().getProperty("db.selectQuery");
	private final String filePath;
	private Document document;
	private Class<U> dataType;
	private Logger logger = LoggerImpl.getLogger();

	public XmlDataHandler(String filePath, Class<U> dataType) {
		super();
		this.filePath = filePath;
		this.dataType = dataType;
	}

	/*
	 * Returns Document type object for the given xml - file
	 */
	protected final Document getDocument() throws DocumentException, SAXException {
		File inputFile = new File(filePath);
		SAXReader xmlReader = XmlReader.getReader();
		document = xmlReader.read(inputFile);
		return document;
	}

	/*
	 * Find child node in the document having node-name: "attribute" & text data =
	 * "value"
	 * 
	 */
	private List<Node> findNodes(Document document, String attribute, String value)
			throws DocumentException, SAXException {
		String query = String.format(this.query, attribute, value);
		XPath xpath = DocumentHelper.createXPath(query);
		return xpath.selectNodes(document);

	}

	/*
	 * Writ document to XML file
	 */
	protected void storeToFile(Document document) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DocumentSource source = new DocumentSource(document);
		File inputFile = new File(this.filePath);
		StreamResult result = new StreamResult(inputFile.getPath());
		transformer.transform(source, result);
	}

	/*
	 * Reads XML file and renders into type U
	 */
	@Override
	public U readDocument() throws ReaderException {
		File file = new File(filePath);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(dataType);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			U result = (U) jaxbUnmarshaller.unmarshal(file);
			return result;
		} catch (JAXBException e) {
			String message = "Error Rendering file for type " + dataType.getName();
			logger.exceptionEncountered(message);
			throw new ReaderException(message, e.getCause());
		}

	}

	/*
	 * Deletes the node with name: "attribute" having text value: "value"
	 */
	@Override
	public void deleteElement(String attribute, String value) {
		try {
			Document doccuemnt = getDocument();
			List<Node> nodes = findNodes(doccuemnt, attribute, value);
			// If no nodes found for query String
			if (nodes.size() == 0) {
				throw new NoRecordFoundException("No Record found with " + attribute + " value : " + value);
			}
			for (int i = 0; i < nodes.size(); i++) {
				nodes.get(i).getParent().remove(nodes.get(i));
			}
			storeToFile(doccuemnt);
		} catch (DocumentException | SAXException | TransformerException e) {
			String message = "Error deleting element with  " + attribute + " with value " + value;
			logger.exceptionEncountered(message);
			throw new DataHandlerException(message, e.getCause());
		}

	}

	/*
	 * Adds data Object of type nodeType to the xml document
	 */
	@Override
	public void addDataNode(Object data, Class nodeType) {
		try {
			String xmlString = XmlUtils.getXmlStringForObject(nodeType.cast(data), nodeType);
			Element newDoc = XmlUtils.getXmlNodeForString(xmlString);
			Document document = getDocument();
			document.getRootElement().add(newDoc);
			storeToFile(document);
		} catch (Exception e) {
			String message = "Error adding new node " + data.toString();
			logger.exceptionEncountered(message);
			throw new DataHandlerException("Error adding new node " + data.toString(), e);
		}

	}
}
