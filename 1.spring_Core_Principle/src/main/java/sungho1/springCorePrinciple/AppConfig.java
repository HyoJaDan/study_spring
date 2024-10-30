package sungho1.springCorePrinciple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sungho1.springCorePrinciple.discount.DiscountPolicy;
import sungho1.springCorePrinciple.discount.FixDiscountPolicy;
import sungho1.springCorePrinciple.discount.RateDiscountPolicy;
import sungho1.springCorePrinciple.member.MemberRepository;
import sungho1.springCorePrinciple.member.MemberService;
import sungho1.springCorePrinciple.member.MemberServiceImpl;
import sungho1.springCorePrinciple.member.MemoryMemberRepository;
import sungho1.springCorePrinciple.order.OrderService;
import sungho1.springCorePrinciple.order.OrderServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
