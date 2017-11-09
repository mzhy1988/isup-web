package com.hns.iusp.ws.xml;

import java.util.HashMap;


public class XmlMarshallerFactory {
	private final static HashMap<String,IXmLMarshaller> processors = new HashMap<String,IXmLMarshaller>();
	static {
		register("",new WSXmlMarshaller());
		register(ObjectXmlMarshaller.FORMAT,new ObjectXmlMarshaller());
		register(WSXmlMarshaller.FORMAT,new WSXmlMarshaller());
	}
	
	public static void register(String msgFormat,IXmLMarshaller processor){
		processors.put(msgFormat, processor);
	}
	
	public static IXmLMarshaller get(String msgFormat) {
		return processors.get(msgFormat!=null?msgFormat:"");
	}
	
	public static IXmLMarshaller getDefault() {
		return get("");
	}
}
