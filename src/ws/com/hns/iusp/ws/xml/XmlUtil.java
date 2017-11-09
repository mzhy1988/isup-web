package com.hns.iusp.ws.xml;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class XmlUtil {
//	private static String replaceReservedChar(String text) {
//		if (text==null||text.length()==0)
//			return text;
//		String s = StringUtil.replace(text, "<", "&lt;");
//		s = StringUtil.replace(s, ">", "&gt;");
//		s = StringUtil.replace(s, "&", "&amp;");
//		s = StringUtil.replace(s, "'", "&apos;");
//		s = StringUtil.replace(s, "\"", "&quot;");
//		return s;
//	}
	
	/**  
     * @param text 
     * @param d  
     * @return  
     */   
	private static String replaceInvaldateCharacter(String text, char d) {   
        if (text != null) {   
            char[] data = text.toCharArray();   
            for (int i = 0; i < data.length; i++) {   
                if(!isXMLCharacter(data[i]))  
                    data[i] = d;   
            }   
            return new String(data);   
        }   
        return "";   
    }   
      
      
    /**  
     * Char ::= #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]  
     * @param c  
     * @return  
     */   
    private static boolean isXMLCharacter(int c) {  
        if (c <= 0xD7FF) {   
            if (c >= 0x20)   
                return true;   
            else    
                return c == '\n' ||  c == '\r' || c == '\t';   
        }   
        return (c>=0xE000 && c<= 0xFFFD) || (c>=0x10000 && c<= 0x10FFFF);   
    }   	

    private static String toBase64( byte [] raw ) {
        if ( raw == null ||raw.length==0) {
          return "";
        }
        try {
    		return new String(Base64Codec.encode(raw),"UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		throw new RuntimeException(e);
    	}
    }	
    
	/**
	 **/
    private static byte[] base64ToByteArray(String base64String) {
		if (base64String==null||base64String.length()==0)
			return new byte[0];
		try {
			return Base64Codec.decode(base64String.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}  
	
	
	public static String toXMLString(Document document) throws Exception{
		return toXMLString(document,"UTF-8");
	}
	
	public static String toXMLString(Document document,String encoding) throws Exception{
		if (document==null)
			return "";
		TransformerFactory   tf=TransformerFactory.newInstance();
		//tf.setAttribute("indent-number", new Integer(2)); 
		Transformer   transformer=tf.newTransformer();     
		DOMSource   source=new  DOMSource(document);  
		transformer.setOutputProperty(OutputKeys.ENCODING,encoding);     
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.setParameter("format-pretty-print",
                                   new Boolean("true"));
		//if (transformer.getOutputProperties().contains(value))
		transformer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "2");
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
		return toXML(xmlString, "UTF-8");
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
