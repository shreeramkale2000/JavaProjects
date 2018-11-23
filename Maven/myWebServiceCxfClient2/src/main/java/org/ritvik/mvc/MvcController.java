package org.ritvik.mvc;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.ritvik.wsdl.calculator.AddRequestType;
import org.ritvik.wsdl.calculator.CalResponseType;
import org.ritvik.wsdl.calculator.CalculatorService;
import org.ritvik.wsdl.calculator.CalculatorServicePortType;
import org.ritvik.wsdl.calculator.StandardType;

@Controller
@RequestMapping("/MvcController")
@Scope("request")
public class MvcController {
	
	private Logger logger = Logger.getLogger(MvcController.class);
	
	@Autowired
	private CalculatorService service;
	@Autowired
	private AddRequestType request;
	@Autowired
	private StandardType header;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String process() {
		logger.info("In MainController");
		
		request.setArgOne(1);
		request.setArgTwo(2);
		
		CalculatorServicePortType port = service.getCalculatorServicePort();
		CalResponseType response = port.addRequest(request, header);
		logger.info("CXF Response Code " + response.getRespCode());
		
		return "redirect:/done.htm";
	}

}
