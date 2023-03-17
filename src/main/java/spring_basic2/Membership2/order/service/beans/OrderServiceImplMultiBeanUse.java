package spring_basic2.Membership2.order.service.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.filter.FilterMultiBeanUse;
import spring_basic2.Membership2.filter.FilterQualifier2;
import spring_basic2.Membership2.filter.MainDiscountPolicy;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.order.Order;
import spring_basic2.Membership2.order.service.OrderService;

@FilterMultiBeanUse
@RequiredArgsConstructor
public class OrderServiceImplMultiBeanUse implements OrderService {
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
