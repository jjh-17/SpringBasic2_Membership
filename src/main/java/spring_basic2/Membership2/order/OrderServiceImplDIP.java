package spring_basic2.Membership2.order;

import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.discount.RateDiscountPolicy;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.MemberRepository;
import spring_basic2.Membership2.member.MemoryMemberRepository;

/*
OrderServiceImpl을 DIP 준수하도록 수정
 */

public class OrderServiceImplDIP implements OrderService {
    //변수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자
    public OrderServiceImplDIP(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //메서드
    @Override
    public Order createOrder(Long memberId, String productName, int productPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, productPrice);

        return new Order(memberId, productName, productPrice, discountPrice);
    }
}
