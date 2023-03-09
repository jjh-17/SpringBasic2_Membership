package spring_basic2.Membership2;

import spring_basic2.Membership2.discount.RateDiscountPolicy;
import spring_basic2.Membership2.member.MemberService;
import spring_basic2.Membership2.member.MemberServiceImplDIP;
import spring_basic2.Membership2.member.MemoryMemberRepository;
import spring_basic2.Membership2.order.OrderService;
import spring_basic2.Membership2.order.OrderServiceImplDIP;

/*
AppDIPConfig의 역할이 잘 보이도록 리펙토링 함
 */
public class AppDIPConfigRefactored {
    public MemberService memberService() {
        return new MemberServiceImplDIP(getRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImplDIP(getRepository(), new RateDiscountPolicy());
    }

    private MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    private MemoryMemberRepository getRepository() {
        return getMemberRepository();
    }

}
