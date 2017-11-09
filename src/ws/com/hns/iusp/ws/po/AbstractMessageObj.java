package com.hns.iusp.ws.po;

import java.io.Serializable;

public abstract class AbstractMessageObj implements Serializable{
	private static final long serialVersionUID = -4519766636564096433L;

	public abstract String toString();
	public String getIsLegacy() {
		return "N";
	}
	public String getTimestamp(){
		return null;
	}

}
