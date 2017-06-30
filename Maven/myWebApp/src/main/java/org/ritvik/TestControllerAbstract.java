package org.ritvik;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class TestControllerAbstract extends AbstractController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Value("${served.msg}")
	private String served_msg;
	
	private Logger logger = Logger.getLogger(TestController.class);

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
		logger.info("TestController handleRequestInternal called...");
		
		TestDaoSpring testDaoSpringImpl = appContext.getBean("testDaoSpringImpl", TestDaoSpring.class);
		
		try {
			response.getWriter().append(served_msg).append(request.getContextPath());
			response.getWriter().append("\nName is: ").append(testDaoSpringImpl.getDate());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
