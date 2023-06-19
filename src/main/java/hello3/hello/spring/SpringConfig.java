package hello3.hello.spring;
import hello3.hello.spring.aop.TimeTraceAop;
import hello3.hello.spring.repository.*;
import hello3.hello.spring.service.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, EntityManager em) {
        this.memberRepository = memberRepository;

    }

    @Bean
    public MemberServices memberService() {
        return new MemberServices(memberRepository);
    }
    
}
