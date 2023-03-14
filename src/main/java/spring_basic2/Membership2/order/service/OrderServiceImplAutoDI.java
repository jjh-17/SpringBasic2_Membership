package spring_basic2.Membership2.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.filter.FilterAutoDI;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.order.Order;

/*
Component Scan이 가능하도록 수정
 */

@FilterAutoDI
public class OrderServiceImplAutoDI implements OrderService {
//    //[Setter 주입]
//    //변수
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    //setter 주입
//    @Autowired(required = false) //required = false : 의존관계를 주입하지 않아도 동작한다.
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("OrderServiceImplAutoDI memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("OrderServiceImplAutoDI discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    //생성자
//    @Autowired //없어도 차이 없음
//    public OrderServiceImplAutoDI(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("OrderServiceImplAutoDI.OrderServiceImplAutoDI");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
//
//    //Configuration Test에서 사용
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }


    //[자동 주입]
    //변수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자
    @Autowired //의존관계 자동 주입 ==> 생성자가 단 1개만 존재하므로 생략 가능
    public OrderServiceImplAutoDI(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
