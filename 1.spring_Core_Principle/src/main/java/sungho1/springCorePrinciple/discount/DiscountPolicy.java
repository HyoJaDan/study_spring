package sungho1.springCorePrinciple.discount;

import sungho1.springCorePrinciple.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
