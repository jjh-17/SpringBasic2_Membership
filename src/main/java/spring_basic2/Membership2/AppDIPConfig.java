package spring_basic2.Membership2;

import spring_basic2.Membership2.discount.RateDiscountPolicy;
import spring_basic2.Membership2.member.MemberService;
import spring_basic2.Membership2.member.MemberServiceImplDIP;
import spring_basic2.Membership2.member.MemoryMemberRepository;
import spring_basic2.Membership2.order.OrderService;
import spring_basic2.Membership2.order.OrderServiceImplDIP;

/*
애플리케이션의 동작에 필요한 구현 객체 생성
생성자를 통해 객체 인스턴스 참조 ==> 생성자를 통해 주입
DIP 준수

중복되는 메서드(MemoryMemberRepository)가 존재
    ==> 역할에 따른 구현이 보이도록 리펙토링이 필요(ctrl + alt + m)
 */
public class AppDIPConfig {
    public MemberService memberService() {
        return new MemberServiceImplDIP(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImplDIP(new MemoryMemberRepository(), new RateDiscountPolicy());
    }

}
