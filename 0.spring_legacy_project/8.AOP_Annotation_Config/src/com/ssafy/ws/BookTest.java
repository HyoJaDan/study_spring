package com.ssafy.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssafy.ws.config.ApplicationConfig;

public class BookTest {
	public static void main(String[] args) {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(ApplicationConfig.class);
		Person person = context.getBean("reader", Person.class);
		try {
			person.doSomething();
		} catch (RestroomException e) {
			System.out.println(e);
		}
	}
}
