package org.ritvik;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/TestControllerModel")
public class TestControllerModel {
	
	private Logger logger = Logger.getLogger("org.ritvik.processLog");
	
	@RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		logger.debug("TestControllerModel printHello called...");
		
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}
	
}