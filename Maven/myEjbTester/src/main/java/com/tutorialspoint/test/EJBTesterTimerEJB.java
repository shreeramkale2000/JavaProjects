package com.tutorialspoint.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.tutorialspoint.timer.TimerSessionBeanRemote;

public class EJBTesterTimerEJB {

	BufferedReader brConsoleReader = null;
	Properties props;
	InitialContext ctx;
	{
		props = new Properties();
		try {
			props.load(ClassLoader.getSystemResourceAsStream("jndi.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			ctx = new InitialContext(props);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
		brConsoleReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	static String toLookup;

	public static void main(String[] args) {
		toLookup = "/myEjbModule/TimerSessionBean!com.tutorialspoint.timer.TimerSessionBeanRemote";

		EJBTesterTimerEJB ejbTester = new EJBTesterTimerEJB();

		ejbTester.testTimerService();
	}

	private void testTimerService() {
		try {
			TimerSessionBeanRemote timerServiceBean = (TimerSessionBeanRemote) ctx.lookup(toLookup);

			System.out.println("[" + (new Date()).toString() + "]" + "timer created.");
			timerServiceBean.createTimer(2000);

		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
}