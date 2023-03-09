package spring_basic2.Membership2.order.service;

import org.springframework.stereotype.Component;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.order.Order;

/*
OrderServiceImpl을 DIP 준수하도록 수정
 */

@Component
public class OrderServiceImplDIPComponent implements OrderService {
    //변수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자
    public OrderServiceImplDIPComponent(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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

    //Configuration Test에서 사용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
