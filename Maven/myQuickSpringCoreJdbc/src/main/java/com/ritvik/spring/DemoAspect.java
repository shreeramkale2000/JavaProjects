package com.ritvik.spring;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DemoAspect {
	
	private Logger logger = Logger.getLogger("org.ritvik.processLog");
	
	@Before("execution(public * getData(*))")
	public void loggingAdvice() {
		logger.debug("Advice Run ");
	}

}
