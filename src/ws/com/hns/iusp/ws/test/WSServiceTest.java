package com.hns.iusp.ws.test;

import java.io.InputStream;
import javax.xml.namespace.QName;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import com.hns.iusp.ws.util.FileUtil;
import junit.framework.TestCase;


public class WSServiceTest extends TestCase {
	RPCServiceClient serviceClient = null;
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public RPCServiceClient getRPCClient() {
		if (serviceClient==null){
			try {
				serviceClient = new RPCServiceClient();
			} catch (AxisFault e) {
				throw new RuntimeException(e);
			}
		}
		return serviceClient;
	}


	public void testPutWitHisPrescription() throws Exception{
		String URL = "http://192.168.20.143:8080/iusp-web/services/SCMService";
		System.err.println("Wit.WebService.URL: " + URL);

		InputStream in = WSServiceTest.class.getResourceAsStream("order.xml");
		try {
			String xml = new String(FileUtil.readBytes(in),"UTF-8");  
			System.out.println(xml);
			String resultXML = invoke(URL , "http://ws.iusp.hns.com", "PUT_Receive",new Object[]{xml,"NJ_XLYY","123"}, 10000);
			System.out.println(resultXML);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			in.close();
		}
	}

	public String invoke(String url, String namespace, String opName, Object[] opArgs, int timeout) throws Exception {
		//QName opQName = new QName("http://service.rmyy.ws.scopetec.com", opName);
		QName opQName = new QName(namespace, opName);
		RPCServiceClient serviceClient = getRPCClient();
		Options options = serviceClient.getOptions();
		options.setTimeOutInMilliSeconds(10000);
		EndpointReference targetEPR = new EndpointReference(url);
		options.setTo(targetEPR);
		try {
			Class<?>[] opReturnType = new Class[] { String.class };
			Object[] response = serviceClient.invokeBlocking(opQName, opArgs, opReturnType);
			if (response!=null&&response.length>0){
				return (String)response[0];
			}
			else
				return null;
		}finally {
			//serviceClient.cleanup();
		}
	}	
}
