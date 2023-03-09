package spring_basic2.Membership2.order.service;

import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.discount.RateDiscountPolicy;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.repository.MemoryMemberRepository;
import spring_basic2.Membership2.order.Order;

/*
현재 OrderServiceImpl 내 코드를 변경해야 할인 정책 변경 가능 ==> OCP 위반
OrderServiceImpl은 DiscountPolicy, FixDiscountPolicy, RateDiscountPolicy에 의존 ==> DIP 위반
 */

public class OrderServiceImpl implements OrderService {

    //변수
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //메서드
    @Override
    public Order createOrder(Long memberId, String productName, int productPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, productPrice);

        return new Order(memberId, productName, productPrice, discountPrice);
    }
}
