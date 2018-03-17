package org.ritvik.axis.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.HandlerRegistry;

import org.apache.axis.AxisProperties;
//import org.apache.axis.client.Stub;
import org.ritvik.wsdl.calculator.AddRequestType;
import org.ritvik.wsdl.calculator.CalResponseType;
import org.ritvik.wsdl.calculator.CalculatorServiceLocator;
import org.ritvik.wsdl.calculator.CalculatorServicePortType;

/**
 * Program for Axis Client
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AxisClient {
	
	static {
		AxisProperties.setProperty("axis.socketSecureFactory","org.apache.axis.components.net.SunFakeTrustSocketFactory");
		
		//AxisProperties.getProperties().put("proxySet", "true");
		//AxisProperties.setProperty("http.proxyHost", "11.222.333.44");
		//AxisProperties.setProperty("http.proxyPort", "80");

		//AxisProperties.setProperty("keystore", "my_keystore.p12");
		//AxisProperties.setProperty("keystorePassword", "abc");
		//AxisProperties.setProperty("keystoreType", "pkcs12");
	}
	
	public static void main(String[] args) {
		URL url = null;
		CalculatorServiceLocator locator = null;
		CalculatorServicePortType port = null;
		AddRequestType request = new AddRequestType();
		CalResponseType response = null;
		
		HandlerRegistry handlerRegistry = null;
		QName qname_service = null;
		List chain = null;
		HandlerInfo info = null;
		try {
			url = new URL("https://localhost:8087/myWebServiceCxfSecure/CxfServices/CalculatorService");
			locator = new CalculatorServiceLocator();
			
			handlerRegistry = locator.getHandlerRegistry();
			qname_service = new QName("http://www.axisbank.com", "EMandateESBServiceHttpPort");
			chain = handlerRegistry.getHandlerChain(qname_service);

			info = new HandlerInfo();
			info.setHandlerClass(AxisLoggingHandler.class);

			chain.add(info);

			port = locator.getCalculatorServicePort(url);
			//((Stub) port).setHeader(sheader);
			
			request.setArgOne(1);
			request.setArgTwo(2);
			
			response = port.addRequest(request);
			System.out.println(response.getResult());
			
		} catch (ServiceException | MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
