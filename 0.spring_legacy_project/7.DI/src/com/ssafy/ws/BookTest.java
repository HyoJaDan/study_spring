package com.ssafy.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BookTest {
	public static void main(String[] args) {
		ApplicationContext context 
			= new GenericXmlApplicationContext("applicationContext.xml");
		Reader reader = context.getBean(Reader.class);
		reader.read();
	}
}
