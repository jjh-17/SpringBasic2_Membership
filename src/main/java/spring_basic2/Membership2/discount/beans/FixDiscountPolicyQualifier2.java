package spring_basic2.Membership2.discount.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.filter.FilterQualifier;
import spring_basic2.Membership2.filter.FilterQualifier2;
import spring_basic2.Membership2.member.Member;

//AppConfigQualifier 만이 스캔하도록 필터링
@FilterQualifier2
public class FixDiscountPolicyQualifier2 implements DiscountPolicy {

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
