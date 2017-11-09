package com.hns.iusp.ws.xml;

import java.util.Map;

import org.w3c.dom.Document;

public interface IXmLMarshaller {
	public Document marshal(Object obj) throws Exception;
	public <T>T unmarshal(Document doc, Class<T> poClass,Map<String,Class> fieldMaps) throws Exception;
}
