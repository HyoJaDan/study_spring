package com.ssafy.ws;

import java.util.Random;

//core concern
public class Reader implements Person {

	@Override
	public void doSomething() throws RestroomException {
		System.out.println("책을 열심히 읽습니다.");
		if(new Random().nextBoolean()) {
			throw new RestroomException();
		}
	}
}
