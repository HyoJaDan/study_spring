package sungho1.springCorePrinciple.Order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sungho1.springCorePrinciple.AppConfig;
import sungho1.springCorePrinciple.member.*;
import sungho1.springCorePrinciple.order.Order;
import sungho1.springCorePrinciple.order.OrderService;
import sungho1.springCorePrinciple.order.OrderServiceImpl;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Member member = new Member(1L,"MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
