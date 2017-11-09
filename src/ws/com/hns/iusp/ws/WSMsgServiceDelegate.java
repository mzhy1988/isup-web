package com.hns.iusp.ws;

import com.hns.iusp.ws.service.WSMsgService;
import com.hns.iusp.ws.service.impl.DefaultWSMsgService;


public class WSMsgServiceDelegate {
	WSMsgService ws = new DefaultWSMsgService();
	private String getErrorResponse(Throwable e){
		try {
			e.printStackTrace();
			String errorXML = ws.getErrorResponse(e);
			System.err.println(errorXML);
			return errorXML;

		} catch (Throwable e1) {
			e.printStackTrace();
			throw new RuntimeException(e1);
		}
	}

	private String getSuccessResponse(long msgid){
		try {
			return ws.getSuccessResponse(msgid);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String PUT_Receive(String dataXML, String username, String password) throws Exception {
		try {
			long msgid = ws.PUT_Receive(dataXML, username, password);
			return getSuccessResponse(msgid);
		}catch (Throwable e){
			return getErrorResponse(e);
		}

	}
}
