package com.ritvik.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public static ApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext("spring.xml");
	}

	public static void main(String[] args) {
		BusinessLogic demo = new BusinessLogic();
		demo.start();
	}
}
