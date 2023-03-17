package spring_basic2.Membership2.lombok;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.configuration.AppConfigLombok;
import spring_basic2.Membership2.configuration.beans.AppConfigFieldMatch;
import spring_basic2.Membership2.discount.beans.RateDiscountPolicyFieldMatch;
import spring_basic2.Membership2.configuration.beans.AppConfigPrimary;
import spring_basic2.Membership2.configuration.beans.AppConfigQualifier;
import spring_basic2.Membership2.configuration.beans.AppConfigQualifier2;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.discount.beans.*;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.service.MemberService;
import spring_basic2.Membership2.member.service.MemberServiceImplLombok;
import spring_basic2.Membership2.member.service.beans.MemberServiceImplFieldMatch;
import spring_basic2.Membership2.member.service.beans.MemberServiceImplPrimary;
import spring_basic2.Membership2.member.service.beans.MemberServiceImplQualifier;
import spring_basic2.Membership2.member.service.beans.MemberServiceImplQualifier2;
import spring_basic2.Membership2.order.service.OrderService;
import spring_basic2.Membership2.order.service.OrderServiceImplLombok;
import spring_basic2.Membership2.order.service.beans.OrderServiceImplFieldMatch;
import spring_basic2.Membership2.order.service.beans.OrderServiceImplPrimary;
import spring_basic2.Membership2.order.service.beans.OrderServiceImplQualifier;
import spring_basic2.Membership2.order.service.beans.OrderServiceImplQualifier2;

public class LombokTest {
    @Test
    @DisplayName("Lombok을 이용해 최적화한 AppConfigLombok 테스트")
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigLombok.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        System.out.println("memberService = " + memberService);

        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
        System.out.println("orderService = " + orderService);

        DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
        System.out.println("discountPolicy = " + discountPolicy);

        MemberServiceImplLombok memberServiceImplLombok = ac.getBean(MemberServiceImplLombok.class);
        Assertions.assertThat(memberRepository).isSameAs(memberServiceImplLombok.getMemberRepository());
        System.out.println("memberServiceImplLombok.getMemberRepository() = " + memberServiceImplLombok.getMemberRepository());

        OrderServiceImplLombok orderServiceImplLombok = ac.getBean(OrderServiceImplLombok.class);
        Assertions.assertThat(memberRepository).isSameAs(orderServiceImplLombok.getMemberRepository());
        Assertions.assertThat(discountPolicy).isSameAs(orderServiceImplLombok.getDiscountPolicy());
        System.out.println("memberServiceImplLombok.getMemberRepository() = " + memberServiceImplLombok.getMemberRepository());
        System.out.println("orderServiceImplLombok.getDiscountPolicy() = " + orderServiceImplLombok.getDiscountPolicy());

        String[] bdNames = ac.getBeanDefinitionNames();
        System.out.println("\n[빈 설정 메타 정보 확인]");
        for (String bdName : bdNames) {
            BeanDefinition bd = ac.getBeanDefinition(bdName);

            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + bdName);
            }
        }
    }

    @Test
    @DisplayName("FieldMatch를 이용해 이중 빈을 해결한 AppConfigFieldMatch 테스트")
    void basicScan2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigFieldMatch.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        System.out.println("memberService = " + memberService);

        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
        System.out.println("orderService = " + orderService);

        DiscountPolicy discountPolicy = ac.getBean(RateDiscountPolicyFieldMatch.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
        System.out.println("discountPolicy = " + discountPolicy);

        MemberServiceImplFieldMatch memberServiceImplFieldMatch = ac.getBean(MemberServiceImplFieldMatch.class);
        Assertions.assertThat(memberRepository).isSameAs(memberServiceImplFieldMatch.getMemberRepository());
        System.out.println("memberServiceImplFieldMatch.getMemberRepository() = " + memberServiceImplFieldMatch.getMemberRepository());

        OrderServiceImplFieldMatch orderServiceImplFieldMatch = ac.getBean(OrderServiceImplFieldMatch.class);
        Assertions.assertThat(memberRepository).isSameAs(orderServiceImplFieldMatch.getMemberRepository());
        Assertions.assertThat(discountPolicy).isSameAs(orderServiceImplFieldMatch.getDiscountPolicy());
        System.out.println("memberServiceImplFieldMatch.getMemberRepository() = " + memberServiceImplFieldMatch.getMemberRepository());
        System.out.println("orderServiceImplFieldMatch.getDiscountPolicy() = " + orderServiceImplFieldMatch.getDiscountPolicy());

        String[] bdNames = ac.getBeanDefinitionNames();
        System.out.println("\n[빈 설정 메타 정보 확인]");
        for (String bdName : bdNames) {
            BeanDefinition bd = ac.getBeanDefinition(bdName);

            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + bdName);
            }
        }
    }

    @Test
    @DisplayName("@Qualifier를 이용해 이중 빈을 해결한 AppConfigQualifier 테스트")
    void basicScan3() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigQualifier.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        System.out.println("memberService = " + memberService);

        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
        System.out.println("orderService = " + orderService);

        DiscountPolicy discountPolicy = BeanFactoryAnnotationUtils
                .qualifiedBeanOfType(ac.getBeanFactory(), DiscountPolicy.class, "rateDiscountPolicy");
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
        System.out.println("discountPolicy = " + discountPolicy);

        MemberServiceImplQualifier memberServiceImplQualifier = ac.getBean(MemberServiceImplQualifier.class);
        Assertions.assertThat(memberRepository).isSameAs(memberServiceImplQualifier.getMemberRepository());
        System.out.println("memberServiceImplQualifier.getMemberRepository() = " + memberServiceImplQualifier.getMemberRepository());

        OrderServiceImplQualifier orderServiceImplQualifier = ac.getBean(OrderServiceImplQualifier.class);
        Assertions.assertThat(memberRepository).isSameAs(orderServiceImplQualifier.getMemberRepository());
        Assertions.assertThat(discountPolicy).isSameAs(orderServiceImplQualifier.getDiscountPolicy());
        System.out.println("memberServiceImplFieldMatch.getMemberRepository() = " + memberServiceImplQualifier.getMemberRepository());
        System.out.println("orderServiceImplFieldMatch.getDiscountPolicy() = " + orderServiceImplQualifier.getDiscountPolicy());

        String[] bdNames = ac.getBeanDefinitionNames();
        System.out.println("\n[빈 설정 메타 정보 확인]");
        for (String bdName : bdNames) {
            BeanDefinition bd = ac.getBeanDefinition(bdName);

            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + bdName);
            }
        }
    }

    @Test
    @DisplayName("사용자 지정 Annotation으로 Qualifier를 정의해 이중 빈을 해결한 AppConfigQualifier2 테스트")
    void basicScan4() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigQualifier2.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        System.out.println("memberService = " + memberService);

        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
        System.out.println("orderService = " + orderService);

        DiscountPolicy discountPolicy = BeanFactoryAnnotationUtils
                .qualifiedBeanOfType(ac.getBeanFactory(), DiscountPolicy.class, "mainDiscountPolicy");
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
        System.out.println("discountPolicy = " + discountPolicy);

        MemberServiceImplQualifier2 memberServiceImplQualifier2 = ac.getBean(MemberServiceImplQualifier2.class);
        Assertions.assertThat(memberRepository).isSameAs(memberServiceImplQualifier2.getMemberRepository());
        System.out.println("memberServiceImplQualifier2.getMemberRepository() = " + memberServiceImplQualifier2.getMemberRepository());

        OrderServiceImplQualifier2 orderServiceImplQualifier2 = ac.getBean(OrderServiceImplQualifier2.class);
        Assertions.assertThat(memberRepository).isSameAs(orderServiceImplQualifier2.getMemberRepository());
        Assertions.assertThat(discountPolicy).isSameAs(orderServiceImplQualifier2.getDiscountPolicy());
        System.out.println("memberServiceImplFieldMatch.getMemberRepository() = " + memberServiceImplQualifier2.getMemberRepository());
        System.out.println("orderServiceImplFieldMatch.getDiscountPolicy() = " + orderServiceImplQualifier2.getDiscountPolicy());

        String[] bdNames = ac.getBeanDefinitionNames();
        System.out.println("\n[빈 설정 메타 정보 확인]");
        for (String bdName : bdNames) {
            BeanDefinition bd = ac.getBeanDefinition(bdName);

            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + bdName);
            }
        }
    }

    @Test
    @DisplayName("@Primary를 이용해 이중 빈을 해결한 AppConfigPrimary 테스트")
    void basicScan5() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigPrimary.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        System.out.println("memberService = " + memberService);

        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
        System.out.println("orderService = " + orderService);

        DiscountPolicy discountPolicy = ac.getBean(RateDiscountPolicyPrimary.class);
        Assertions.assertThat(discountPolicy).isInstanceOf(DiscountPolicy.class);
        System.out.println("discountPolicy = " + discountPolicy);

        MemberServiceImplPrimary memberServiceImplPrimary = ac.getBean(MemberServiceImplPrimary.class);
        Assertions.assertThat(memberRepository).isSameAs(memberServiceImplPrimary.getMemberRepository());
        System.out.println("memberServiceImplPrimary.getMemberRepository() = " + memberServiceImplPrimary.getMemberRepository());

        OrderServiceImplPrimary orderServiceImplPrimary = ac.getBean(OrderServiceImplPrimary.class);
        Assertions.assertThat(memberRepository).isSameAs(orderServiceImplPrimary.getMemberRepository());
        Assertions.assertThat(discountPolicy).isSameAs(orderServiceImplPrimary.getDiscountPolicy());
        System.out.println("memberServiceImplFieldMatch.getMemberRepository() = " + memberServiceImplPrimary.getMemberRepository());
        System.out.println("orderServiceImplFieldMatch.getDiscountPolicy() = " + orderServiceImplPrimary.getDiscountPolicy());

        String[] bdNames = ac.getBeanDefinitionNames();
        System.out.println("\n[빈 설정 메타 정보 확인]");
        for (String bdName : bdNames) {
            BeanDefinition bd = ac.getBeanDefinition(bdName);

            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + bdName);
            }
        }
    }
}
