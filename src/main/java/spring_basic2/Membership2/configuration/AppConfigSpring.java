package spring_basic2.Membership2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.discount.RateDiscountPolicy;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.service.MemberService;
import spring_basic2.Membership2.member.service.MemberServiceImplDIP;
import spring_basic2.Membership2.member.repository.MemoryMemberRepository;
import spring_basic2.Membership2.order.service.OrderService;
import spring_basic2.Membership2.order.service.OrderServiceImplDIP;

/*
순수 자바 코드로 DI를 적용한 AppDIPConfig, AppDIPConfigRefactored와 달리,
스프링을 사용하여 DI를 적용
 */
@Configuration //설정을 구성한다고 명시 ==> 클래스를 구성 정보로써 사용
public class AppConfigSpring {
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
