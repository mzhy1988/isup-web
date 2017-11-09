package com.hns.iusp.ws.service.impl;


import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.hns.iusp.ws.po.Receive;
import com.hns.iusp.ws.service.WSMsgService;
import com.hns.iusp.ws.util.MessagePacket;
import com.hns.iusp.ws.util.WSUtil;
import com.hns.iusp.ws.xml.IXmLMarshaller;
import com.hns.iusp.ws.xml.XmlMarshallerFactory;
import com.hns.iusp.ws.xml.XmlUtil;



public class DefaultWSMsgService implements WSMsgService{

	private String validateUser(String username,String password) throws Exception{


		return "XXX";
	}	

	public long PUT_Receive(String dataXML, String username, String password) throws Exception {

		IXmLMarshaller xm = XmlMarshallerFactory.get("");
		if (xm==null)
			throw new Exception("No XmlMarshaller");
		Document doc = XmlUtil.toXML(dataXML,"UTF8");
		if (doc==null)
			throw new Exception("Invalid xml data");
		HashMap<String,Class> fieldMaps = new HashMap<String,Class>();
		fieldMaps.put("datas", Receive.class);
		MessagePacket sm = xm.unmarshal(doc, MessagePacket.class, fieldMaps);
		if (sm.getDatas().size()==0)
			throw new Exception("Invalid xml content");
		Object[] datas = sm.getDataArray();
		if (datas==null)
			throw new Exception("No data");
		for (Object data : datas){
			if (!(data instanceof Receive)){
				throw new Exception("MO class error: " + data.getClass().getName());
			}
			Receive po = (Receive)data;
			System.out.println("=====================");
		}
		return 1;

		//return putMessage(MessageTypes.SCM_Requisition, dataXML, username, password);
	}
	public String getErrorResponse(Throwable e) throws Exception{
		try {
			e.printStackTrace();//日志输出异常原因
			String errorXML = WSUtil.toXMLString(getErrorXML(e.getMessage()!=null?e.getMessage():e.toString()));
			System.err.println(errorXML);
			return errorXML;

		} catch (Throwable e1) {
			e.printStackTrace();
			throw new RuntimeException(e1);
		}
	}

	public String getSuccessResponse(long msgid) throws Exception{
		try {
			return WSUtil.toXMLString(getSuccessXML(msgid>0?(""+msgid):""));
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Document getSuccessXML(String msgID) throws Exception{
		DocumentBuilderFactory   factory=DocumentBuilderFactory.newInstance();     
		DocumentBuilder builder=factory.newDocumentBuilder();   
		Document doc=builder.newDocument();
		Element rootnode = doc.createElement("xml");
		doc.appendChild(rootnode);
		Element node = doc.createElement("sys");
		rootnode.appendChild(node);
		Element statusnode = doc.createElement("status");
		statusnode.setTextContent("ACK");
		node.appendChild(statusnode);
		Element appnode = doc.createElement("app");
		appnode.setTextContent("SCM");
		node.appendChild(appnode);
		Element sitenode = doc.createElement("site");
		node.appendChild(sitenode);
		Element vernode = doc.createElement("ver");
		node.appendChild(vernode);
		if (msgID!=null&&msgID.length()>0){
			Element msgidnode = doc.createElement("msgid");
			msgidnode.setTextContent(msgID);
			node.appendChild(msgidnode);
		}
		//		Element errornode = doc.createElement("errordescription");
		//		errornode.setTextContent(error);
		//		node.appendChild(errornode);
		Element tsnode = doc.createElement("timestamp");
		node.appendChild(tsnode);

		return doc;
	}

	public Document getErrorXML(String error) throws Exception{
		DocumentBuilderFactory   factory=DocumentBuilderFactory.newInstance();     
		DocumentBuilder builder=factory.newDocumentBuilder();   
		Document doc=builder.newDocument();
		Element rootnode = doc.createElement("xml");
		doc.appendChild(rootnode);
		Element node = doc.createElement("sys");
		rootnode.appendChild(node);
		Element statusnode = doc.createElement("status");
		statusnode.setTextContent("NAK");
		node.appendChild(statusnode);
		Element appnode = doc.createElement("app");
		appnode.setTextContent("SCM");
		node.appendChild(appnode);
		Element sitenode = doc.createElement("site");
		node.appendChild(sitenode);
		Element vernode = doc.createElement("ver");
		node.appendChild(vernode);
		Element errornode = doc.createElement("error");
		errornode.setTextContent(error);
		node.appendChild(errornode);
		Element tsnode = doc.createElement("timestamp");
		node.appendChild(tsnode);
		//old version
		Element errornode1 = doc.createElement("errordescription");
		errornode1.setTextContent(error);
		node.appendChild(errornode1);
		Element tsnode1 = doc.createElement("errordate");
		node.appendChild(tsnode1);

		return doc;
	}
}
