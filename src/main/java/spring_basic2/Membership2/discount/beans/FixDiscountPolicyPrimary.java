package spring_basic2.Membership2.discount.beans;

import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.filter.FilterFieldMatch;
import spring_basic2.Membership2.filter.FilterPrimary;
import spring_basic2.Membership2.member.Member;

@FilterPrimary
public class FixDiscountPolicyPrimary implements DiscountPolicy {

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
