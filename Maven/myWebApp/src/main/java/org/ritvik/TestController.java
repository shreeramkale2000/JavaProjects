package org.ritvik;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class TestController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Value("${served.msg}")
	private String served_msg;
	
	private Logger logger = Logger.getLogger(TestController.class);

	@RequestMapping(value = "/TestController", method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Entered TestController");
		TestDaoSpring testDaoSpringImpl = appContext.getBean("testDaoSpringImpl", TestDaoSpring.class);
		
		response.getWriter().append(served_msg).append(request.getContextPath());
		response.getWriter().append("\nName is: ").append(testDaoSpringImpl.getDate());
		
		return null;
	}

}
