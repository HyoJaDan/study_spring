package com.ssafy.ws;

import org.aspectj.lang.ProceedingJoinPoint;

//Advice Cross cutting concern 구현 - before, after, around, after-throwing, after-reurnning
public class BookAspect {
	//코드작성
	public void before() {
		System.out.println("도서관에 갑니다.");
	}
	
	public void after() {
		System.out.println("집으로 돌아갑니다.");
	}
	
	public void afterReturning() {
		System.out.println("읽은 것을 정리합니다.");
	}
	
	public void afterThrowing() {
		System.out.println("화장실에 갑니다.");
	}
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable  {
		String signature = joinPoint.getSignature().toShortString();
		System.out.println(signature+" 시작");
		try {
			return joinPoint.proceed();
		} finally {
			System.out.println(signature+" 종료");
		}
		
	}
}
