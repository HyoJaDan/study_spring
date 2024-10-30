package com.ssafy.ws;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Advice Cross cutting concern 구현 - before, after, around, after-throwing, after-reurnning
@Component
@Aspect
public class BookAspect {
	
	@Before("execution(public * com.ssafy.ws.*.doSomething())")
	public void before() {
		System.out.println("도서관에 갑니다.");
	}
	
	@After("execution(public * com.ssafy.ws.*.doSomething())")
	public void after() {
		System.out.println("집으로 돌아갑니다.");
	}
	
	@AfterReturning("execution(public * com.ssafy.ws.*.doSomething())")
	public void afterReturning() {
		System.out.println("읽은 것을 정리합니다.");
	}

	@AfterThrowing("execution(public * com.ssafy.ws.*.doSomething())")
	public void afterThrowing() {
		System.out.println("화장실에 갑니다.");
	}
	
	@Around("execution(public * com.ssafy.ws.*.doSomething())")
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
