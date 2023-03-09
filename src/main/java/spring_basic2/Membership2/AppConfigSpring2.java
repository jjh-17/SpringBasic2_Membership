package spring_basic2.Membership2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.discount.RateDiscountPolicy;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.repository.MemoryMemberRepository;
import spring_basic2.Membership2.member.service.MemberService;
import spring_basic2.Membership2.member.service.MemberServiceImplDIP;
import spring_basic2.Membership2.order.service.OrderService;
import spring_basic2.Membership2.order.service.OrderServiceImplDIP;

/*
AppConfigSpring에서 @Configuration을 제거하였을 때 Singleton을 만족하는지 확인하기 위한 클래스
 */
public class AppConfigSpring2 {
    @Bean //아래 메서드를 스프링 컨테이너에 스프링 빈으로 등록한다.
    public MemberService memberService() {
        //생성자 주입
        return new MemberServiceImplDIP(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImplDIP(memberRepository(), discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
