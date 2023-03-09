package spring_basic2.Membership2.discount;

import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

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
