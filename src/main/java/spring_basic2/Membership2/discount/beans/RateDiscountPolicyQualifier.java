package spring_basic2.Membership2.discount.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.filter.FilterLombok;
import spring_basic2.Membership2.filter.FilterQualifier;
import spring_basic2.Membership2.member.Member;

//AppConfigQualifier 만이 스캔하도록 필터링
@FilterQualifier
@Qualifier("rateDiscountPolicy") //동일한 타입의 빈을 구분하기 위한 추가 구분자 ==> 빈 이름을 변경하는 것이 아님
public class RateDiscountPolicyQualifier implements DiscountPolicy {

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
