package spring_basic2.Membership2.discount;

import spring_basic2.Membership2.member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price); //할인 대상 금액 return

}
