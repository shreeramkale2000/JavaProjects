package org.ritvik.cxf.service;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.ritvik.wsdl.calculator.AddRequestType;
import org.ritvik.wsdl.calculator.CalResponseType;
import org.ritvik.wsdl.calculator.CalculatorServicePortType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import com.parasoft.wsdl.calculator.Calculator;
import com.parasoft.wsdl.calculator.ICalculator;

public class CxfService implements CalculatorServicePortType {
	
	@Autowired
	private StringWriter stackTraceWriter;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Value("${default.success.code}")
	private String default_success_code;
	
	@Value("${default.success.desc}")
	private String default_success_desc;
	
	private Logger logger = Logger.getLogger(CxfService.class);
	
	public CalResponseType addRequest (AddRequestType addRequest) {
		logger.info("WS Security Passed for Calculator Service");
		CalResponseType addResponse = appContext.getBean("addResponse", CalResponseType.class);
		
		Calculator parasoftService = appContext.getBean("parasoftService", Calculator.class);
		ICalculator parasoftPort = parasoftService.getICalculator();
		
		CxfServiceDao cxfServiceDaoImpl = appContext.getBean("cxfServiceDaoImpl", CxfServiceDao.class);
		try {
			addResponse.setResult(parasoftPort.add(addRequest.getArgOne(), addRequest.getArgTwo()));
			addResponse.setRespCode(default_success_code);
			addResponse.setRespMsg(default_success_desc + " at " + cxfServiceDaoImpl.getDate());
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(stackTraceWriter));
			logger.fatal(stackTraceWriter.toString());
			stackTraceWriter.flush();
		}

		return addResponse;
	}
	
}