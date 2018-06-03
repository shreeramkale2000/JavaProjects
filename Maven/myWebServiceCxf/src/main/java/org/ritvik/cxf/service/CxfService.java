package org.ritvik.cxf.service;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.ritvik.wsdl.calculator.AddRequestType;
import org.ritvik.wsdl.calculator.CalResponseType;
import org.ritvik.wsdl.calculator.CalculatorServicePortType;
import org.ritvik.wsdl.calculator.StandardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

public class CxfService implements CalculatorServicePortType {
	
	@Autowired
	private StringWriter stackTraceWriter;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Value("${default.success.code}")
	private String default_success_code;
	
	@Value("${default.success.desc}")
	private String default_success_desc;
	
	private Logger logger = Logger.getLogger("org.ritvik.processLog");
	
	public CalResponseType addRequest (AddRequestType addRequest, StandardType header) {
		logger.debug("WS Security Passed for Calculator Service");
		CalResponseType addResponse = appContext.getBean("addResponse", CalResponseType.class);
		
		try {
			addResponse.setResult(addRequest.getArgOne() + addRequest.getArgTwo());
			addResponse.setRespCode(default_success_code);
			addResponse.setRespMsg(default_success_desc);
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(stackTraceWriter));
			logger.fatal(stackTraceWriter.toString());
			stackTraceWriter.flush();
		}

		return addResponse;
	}
	
}