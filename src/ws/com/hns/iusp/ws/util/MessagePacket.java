package com.hns.iusp.ws.util;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "xml")
public class MessagePacket {
	private String version;
	private Timestamp timestamp;
	
	private ArrayList<Object> datas = new ArrayList<Object>();

	public MessagePacket() {

	}

	public MessagePacket(Object[] aDatas) {
		if (datas!=null){
			for (Object data:aDatas){
				datas.add(data);
			}
		}
	}
	
	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	public ArrayList<Object> getDatas() {
		return datas;
	}

	public void setDatas(ArrayList<Object> datas) {
		this.datas = datas;
	}
	
	public Object[] getDataArray() {
		return this.datas.toArray();
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
