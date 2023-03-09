package spring_basic2.Membership2.discount;

import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.Member;

/*
고정 할인률
 */
public class FixDiscountPolicy implements DiscountPolicy {

    //변수
    private int fixedDiscountAmount = 1000;

    //메서드
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return fixedDiscountAmount;
        }
        return 0;
    }
}
