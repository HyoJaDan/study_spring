package com.ssafy.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BookTest {
	public static void main(String[] args) {
		ApplicationContext context =
				new GenericXmlApplicationContext("applicationContext.xml");
		Person person = context.getBean("reader", Person.class);
		try {
			person.doSomething();
		} catch (RestroomException e) {
			System.out.println(e);
		}
	}
}
