package sungho1.springCorePrinciple.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sungho1.springCorePrinciple.discount.DiscountPolicy;
import sungho1.springCorePrinciple.member.Member;
import sungho1.springCorePrinciple.member.MemberRepository;
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId,String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        int discountPrice=discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
