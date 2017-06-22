package com.ritvik.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	public static ApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext("spring.xml");
	}

	public static void main(String[] args) {
		BusinessLogic businessLogic = context.getBean("businessLogic", BusinessLogic.class);
		businessLogic.start();
	}
}
