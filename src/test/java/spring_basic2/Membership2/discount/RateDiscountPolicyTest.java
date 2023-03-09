package spring_basic2.Membership2.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    //변수
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    //테스트
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야한다.")
    void testVipDiscount(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC은 10% 할인이 적용되어야한다.")
    void testBasicDiscount(){
        //given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(0);
    }
}