package hello3.hello.spring.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Aspect
@Controller
public class TimeTraceAop {
    //0.그리 ㅁ부분에서 검은색 부분이다. 공통 관심사를 타겟팅을 해준다. 아래 뜻은 패키지 하위폴더는 모두 적용해라 라는뜻
    @Around("execution(* hello3.hello.spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        // 1. 어떤 메서드를 호출하는지 볼수 있다.
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
