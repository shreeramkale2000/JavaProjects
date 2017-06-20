package org.ritvik;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class TestController extends AbstractController {
	
	@Autowired
	private ApplicationContext appContext;
	
	@Value("${served.msg}")
	private String served_msg;
	
	private Logger logger = Logger.getLogger(TestController.class);

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Entered TestController");
		TestDaoSpringTx testDaoSpringTx = appContext.getBean("testDaoSpringTx", TestDaoSpringTx.class);
		
		response.getWriter().append(served_msg).append(request.getContextPath());
		response.getWriter().append("\nName is: ").append(testDaoSpringTx.getDate());
		
		return null;
	}

}
