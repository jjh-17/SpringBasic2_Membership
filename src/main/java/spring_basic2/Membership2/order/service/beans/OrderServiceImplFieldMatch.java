package spring_basic2.Membership2.order.service.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.filter.FilterFieldMatch;
import spring_basic2.Membership2.filter.FilterLombok;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.order.Order;
import spring_basic2.Membership2.order.service.OrderService;

//AppConfigFieldMatch 만이 스캔하도록 필터링
@FilterFieldMatch
@RequiredArgsConstructor
public class OrderServiceImplFieldMatch implements OrderService {
    /*
    @Autowired는 타입-필드 이름-파라미터 이름으로 빈 이름을 매칭한다.
    필드 이름을 매칭되기를 원하는 빈 이름으로 변경하여 충돌을 회피한다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy rateDiscountPolicyFieldMatch;

    //메서드
    @Override
    public Order createOrder(Long memberId, String productName, int productPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = rateDiscountPolicyFieldMatch.discount(member, productPrice);

        return new Order(memberId, productName, productPrice, discountPrice);
    }

    //LombokTest에서 사용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public DiscountPolicy getDiscountPolicy() {
        return rateDiscountPolicyFieldMatch;
    }
}
