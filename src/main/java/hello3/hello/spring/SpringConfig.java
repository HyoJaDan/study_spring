package hello3.hello.spring;
import hello3.hello.spring.repository.MemberRepository;
import hello3.hello.spring.repository.MemoryMemberRepository;
import hello3.hello.spring.service.MemberServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringConfig {
    @Bean
    public MemberServices memberService() {
        return new MemberServices(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}