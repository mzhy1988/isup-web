package com.hns.iusp.ws.xml;


/**
 * 原WebService XML格式解析器
 *
 */
public class WSXmlMarshaller extends ObjectXmlMarshaller{
	public final static String FORMAT="ws";
	
	public WSXmlMarshaller() {
		super(true,true);
	}
	
	public WSXmlMarshaller(boolean keepEmtpyTag){
		super(keepEmtpyTag,true);
	}
	
}
