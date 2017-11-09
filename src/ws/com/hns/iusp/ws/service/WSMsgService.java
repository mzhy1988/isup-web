package com.hns.iusp.ws.service;


public interface WSMsgService {
	public String getSuccessResponse(long msgid) throws Exception;
	public String getErrorResponse(Throwable e) throws Exception;
	
	public long PUT_Receive(String dataXML, String username, String password) throws Exception ;
}
