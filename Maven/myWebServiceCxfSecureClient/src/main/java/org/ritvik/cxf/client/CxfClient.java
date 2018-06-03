package org.ritvik.cxf.client;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.ritvik.wsdl.calculator.AddRequestType;
import org.ritvik.wsdl.calculator.CalResponseType;
import org.ritvik.wsdl.calculator.CalculatorService;
import org.ritvik.wsdl.calculator.CalculatorServicePortType;
import org.ritvik.wsdl.calculator.StandardType;

/**
 * Program for CXF Client
 */
@SuppressWarnings("resource")
public class CxfClient {
	private Logger logger = Logger.getLogger("org.ritvik.processLog");
	private StringWriter stackTraceWriter = new StringWriter();
	
	public static void main(String[] args) {
		//-Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=8080
		CxfClient cxf = new CxfClient();
		cxf.runCxfClient();
	}
	
	public void runCxfClient(){
		SpringBusFactory bf = new SpringBusFactory();
		BusFactory.setDefaultBus(bf.createBus("ClientConfig.xml"));
		
		ApplicationContext context = new ClassPathXmlApplicationContext("ClientConfig.xml");

		CalculatorService service = context.getBean("service", CalculatorService.class);
		CalculatorServicePortType port = service.getPort(CalculatorServicePortType.class);

		//BindingProvider bindingprovider = (BindingProvider) port;
		//bindingprovider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
		
		AddRequestType addRequest = context.getBean("addRequest", AddRequestType.class);
		StandardType header = context.getBean("header", StandardType.class);
		CalResponseType resp = null;
		logger.debug("Invoking CalculatorService...");
		try {
			resp = port.addRequest(addRequest, header);
			logger.debug(resp.getResult());
			logger.debug(resp.getRespCode());
			logger.debug(resp.getRespMsg());
		} catch (Exception e) {
			logger.fatal("Invocation failed with the following: " + e.getCause());
			e.printStackTrace(new PrintWriter(stackTraceWriter));
			logger.debug(stackTraceWriter.toString());
			stackTraceWriter.flush();
		}
	}
}
