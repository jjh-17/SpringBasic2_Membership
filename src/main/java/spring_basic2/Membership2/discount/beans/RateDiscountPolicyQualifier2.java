package spring_basic2.Membership2.discount.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.filter.FilterQualifier;
import spring_basic2.Membership2.filter.FilterQualifier2;
import spring_basic2.Membership2.filter.MainDiscountPolicy;
import spring_basic2.Membership2.member.Member;

//AppConfigQualifier 만이 스캔하도록 필터링
@FilterQualifier2
@MainDiscountPolicy //사용자 지정 Annotation을 이용하여 Qualifier 지정
public class RateDiscountPolicyQualifier2 implements DiscountPolicy {

    //변수
    private int ratedDiscountAmount = 10;

    //메서드
    //메서드 위에 ctrl+shift+enter하면 새로운 데스트 자동 완성 가능
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * ratedDiscountAmount / 100;
        }
        return 0;
    }
}
