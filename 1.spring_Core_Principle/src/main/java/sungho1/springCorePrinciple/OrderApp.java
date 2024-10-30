package sungho1.springCorePrinciple;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sungho1.springCorePrinciple.member.Grade;
import sungho1.springCorePrinciple.member.Member;
import sungho1.springCorePrinciple.member.MemberService;
import sungho1.springCorePrinciple.member.MemberServiceImpl;
import sungho1.springCorePrinciple.order.Order;
import sungho1.springCorePrinciple.order.OrderService;
import sungho1.springCorePrinciple.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        //AppConfig appConfig=new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        OrderService orderService =applicationContext.getBean("orderService",OrderService.class);

        Long memberId = 1L;
        Member member = new Member (memberId, "memberA",Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder (memberId, "itemA",10000);
        System.out.println("Order = " + order);
    }
}
