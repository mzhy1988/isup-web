package com.hns.iusp.ws.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

 

public class WSUtil {
	
	public static String toXMLString(Document document) throws Exception{
		if (document==null)
			return "";
		TransformerFactory   tf=TransformerFactory.newInstance();     
		Transformer   transformer=tf.newTransformer();     
		DOMSource   source=new  DOMSource(document);  
		String encoding ="UTF-8";
		transformer.setOutputProperty(OutputKeys.ENCODING,encoding);     
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.setParameter("format-pretty-print",
                                   new Boolean("true"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStreamWriter   pw=new   OutputStreamWriter(out,encoding);
		try {
			StreamResult   result=new   StreamResult(pw);     
			transformer.transform(source,result);
			pw.flush();
			out.flush();
		}finally {
			pw.close();
			out.close();
		}
		return new String(out.toByteArray(),encoding);
	}
	
	public static Document toXML(String xmlString) throws Exception{
		String encoding = "UTF-8";
		return toXML(xmlString, encoding);
	}
	public static Document toXML(String xmlString,String encoding) throws Exception{
		if (xmlString==null||xmlString.length()==0)
			return null;
		DocumentBuilderFactory factory = null; 
		DocumentBuilder builder = null; 
		InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes(encoding));
		
		Document doc = null;
		
		try {
			 factory=DocumentBuilderFactory.newInstance(); 
			 builder=factory.newDocumentBuilder(); 
			 doc = builder.parse(inputStream);
			 return doc;
		} finally{
			inputStream.close();
		}		
	}
}
