package org.ritvik.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/WorkController")
@Scope("request")
public class WorkController {
	
	@Autowired
	private Logger logger;
	
	@RequestMapping(method = RequestMethod.GET)
	public String processRequest() {
		logger.info("WorkController");
		return "/work.htm";
	}

}
