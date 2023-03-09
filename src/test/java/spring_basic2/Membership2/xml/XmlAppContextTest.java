package spring_basic2.Membership2.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.service.MemberService;
import spring_basic2.Membership2.order.service.OrderService;

public class XmlAppContextTest {
    @Test
    @DisplayName("XML 기반 AppConfigSpring")
    void xmlAppContext(){
        //given
        ApplicationContext ac = new GenericXmlApplicationContext("appConfigSpring.xml");

        //when
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        DiscountPolicy discountPolicy = ac.getBean("discountPolicy", DiscountPolicy.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        //then
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);

        System.out.println("[XML 기반 AppConfigSpring]");
        System.out.println("memberService = " + memberService);
        System.out.println("discountPolicy = " + discountPolicy);
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("orderService = " + orderService + '\n');
    }
}
