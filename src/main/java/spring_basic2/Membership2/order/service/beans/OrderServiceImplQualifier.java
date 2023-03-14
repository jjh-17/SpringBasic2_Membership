package spring_basic2.Membership2.order.service.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.filter.FilterLombok;
import spring_basic2.Membership2.filter.FilterQualifier;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.order.Order;
import spring_basic2.Membership2.order.service.OrderService;

//AppConfigQualifier 만이 스캔하도록 필터링
@FilterQualifier
//@RequiredArgsConstructor //final이 붙은 필드를 모아서 생성자 자동 생성
public class OrderServiceImplQualifier implements OrderService {
    //[자동 주입]
    //변수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //Qualifier를 이용해 추가한 구분자로 다수의 빈 중 하나를 지정 가능
    //  ==> @RequiredArgsConstructor로 생성자를 생략하면 지정을 할 수 없는 것으로 보인다.
    @Autowired
    public OrderServiceImplQualifier(MemberRepository memberRepository, @Qualifier("rateDiscountPolicy") DiscountPolicy discountPolicy) {
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

    //LombokTest에서 사용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}
