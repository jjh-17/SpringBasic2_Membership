package spring_basic2.Membership2.order.service.beans;

import lombok.RequiredArgsConstructor;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.filter.FilterLombok;
import spring_basic2.Membership2.filter.FilterPrimary;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.order.Order;
import spring_basic2.Membership2.order.service.OrderService;

//AppConfigLombok 만이 스캔하도록 필터링
@FilterPrimary
@RequiredArgsConstructor //final이 붙은 필드를 모아서 생성자 자동 생성
public class OrderServiceImplPrimary implements OrderService {
    //[자동 주입]
    //변수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

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
