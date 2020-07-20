package com.helper;

import com.model.exception.ReaderException;

public interface DataHandler<U> {

	public U readDocument() throws ReaderException;

	public void deleteElement(String keyField, String keyValue);

	public void addDataNode(Object data, Class nodeType);


}
