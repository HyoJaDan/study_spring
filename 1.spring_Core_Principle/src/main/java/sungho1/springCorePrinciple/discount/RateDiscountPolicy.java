package sungho1.springCorePrinciple.discount;

import org.springframework.stereotype.Component;
import sungho1.springCorePrinciple.member.Grade;
import sungho1.springCorePrinciple.member.Member;

@Component
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price)
    {
        if(member.getGrade()== Grade.VIP){
            return price * discountPercent/100;
        }else{
            return 0;
        }
    }
}
