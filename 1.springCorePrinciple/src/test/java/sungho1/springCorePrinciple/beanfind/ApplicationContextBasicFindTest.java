package sungho1.springCorePrinciple.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sungho1.springCorePrinciple.AppConfig;
import sungho1.springCorePrinciple.member.MemberService;
import sungho1.springCorePrinciple.member.MemberServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 이름으로 조회") void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // 멤버 서비스가 멤버 서비스 임플의 인스턴스면 성공
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입만으로 조회") void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    // 이 코드는 좋지는 않다. 역할에 의존해야지 구현에 구현하기 떄문이다.
    @Test
    @DisplayName("구체 타입(impl)으로 조회") void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    // 중요 오류상황도 꼭 테스트 케이스를 넣어야한다.
    @Test
    @DisplayName("빈 이름으로 조회X") void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        // 2번째 줄의 코드가 실행되면, NoSuchBeanDefinitionException오류가 터져야 성공이다.
        assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("xxxxx", MemberService.class));
    }
}
