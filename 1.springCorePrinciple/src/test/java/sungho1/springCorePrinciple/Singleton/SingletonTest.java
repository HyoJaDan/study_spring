package sungho1.springCorePrinciple.Singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sungho1.springCorePrinciple.AppConfig;
import sungho1.springCorePrinciple.member.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureSingletonTest(){
        AppConfig appConfig=new AppConfig();

        //먼저 2개의 memberService를 만든다. ( 예제 : 두 명의 회원)
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1 );
        System.out.println("memberService2 = " + memberService2 );

        assertThat(memberService1).isNotSameAs(memberService2);
    }
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        assertThat(singletonService1).isSameAs(singletonService2);

        System.out.println("singletonService1 ="  + singletonService1);
        System.out.println("singletonService2 ="  + singletonService2);
    }
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1 );
        System.out.println("memberService2 = " + memberService2 );

        assertThat(memberService1).isSameAs(memberService2);
    }
}
