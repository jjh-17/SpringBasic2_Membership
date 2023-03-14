package spring_basic2.Membership2.discount.beans;

import org.springframework.context.annotation.Primary;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.filter.FilterLombok;
import spring_basic2.Membership2.filter.FilterPrimary;
import spring_basic2.Membership2.member.Member;

@FilterPrimary
@Primary //동일한 타입의 빈이 여러 개 등록되었을 때, 해당 타입의 빈을 호출하면 @Primary가 우선권을 가진다.
public class RateDiscountPolicyPrimary implements DiscountPolicy {

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
