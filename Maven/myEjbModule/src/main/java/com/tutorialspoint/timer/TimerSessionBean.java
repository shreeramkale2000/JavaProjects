package com.tutorialspoint.timer;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

@Stateless
public class TimerSessionBean implements TimerSessionBeanRemote {

	@Resource
	private SessionContext context;

	@Override
	public void createTimer(long milliseconds) {
		context.getTimerService().createTimer(milliseconds, "Hello World!");
	}

	@Timeout
	public void timeOutHandler(Timer timer) {
		System.out.println("timeoutHandler : " + timer.getInfo());
		timer.cancel();
	}
	
}