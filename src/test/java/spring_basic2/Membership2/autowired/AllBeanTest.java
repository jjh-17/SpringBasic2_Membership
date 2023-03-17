package spring_basic2.Membership2.autowired;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic2.Membership2.configuration.beans.AppConfigMultiBeanUse;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.discount.FixDiscountPolicy;
import spring_basic2.Membership2.discount.RateDiscountPolicy;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.Member;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    static class DiscountService {
        //동일한 타입의 빈 여러개를 모두 사용하기 위해 map, list 사용
        private final Map<String, DiscountPolicy> policy_map;
        private final List<DiscountPolicy> policy_list;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policy_map, List<DiscountPolicy> policy_list) {
            this.policy_map = policy_map;
            this.policy_list = policy_list;
            System.out.println("policy_map = " + policy_map);
            System.out.println("policy_list = " + policy_list);
        }

        public int getDiscount(Member member, int price, String discount_code) {
            DiscountPolicy discountPolicy = policy_map.get(discount_code);

            System.out.println("\n[" + member.getName() + ", " + member.getGrade() + "]");
            System.out.println("discount_code = " + discount_code);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price); //member에게 적용되는 할인값
        }
    }

    static class DiscountService2 {
        private final Map<String, DiscountPolicy> policy_map;
        private final List<DiscountPolicy> policy_list;

        public DiscountService2(Map<String, DiscountPolicy> policy_map, List<DiscountPolicy> policy_list) {
            this.policy_map = policy_map;
            this.policy_list = policy_list;
            System.out.println("policy_map = " + policy_map);
            System.out.println("policy_list = " + policy_list);
        }

        public int getDiscount(Member member, int price, String discount_code) {
            DiscountPolicy discountPolicy = policy_map.get(discount_code);

            System.out.println("\n[" + member.getName() + ", " + member.getGrade() + "]");
            System.out.println("discount_code = " + discount_code);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price); //member에게 적용되는 할인값
        }
    }

    @Configuration
    static class DiscountService2Config {
        @Bean
        public FixDiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

        @Bean
        public RateDiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
    }

    @Test
    @DisplayName("ComponentScan으로 동일한 타입의 다중 빈 다루기")
    void findEveryBean() {
        //@Autowired에 의해 DiscountPolicy를 포함하는 map, list에 DiscountPolicy형 빈 들이 저장된다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigMultiBeanUse.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member userA = new Member(1L, "userA", Grade.VIP);
        Member userB = new Member(2L, "userB", Grade.BASIC);

        int discountPriceA_fix = discountService.getDiscount(userA, 10000, "fixDiscountPolicyMultiBeanUse");
        System.out.println("discountPriceA_fix = " + discountPriceA_fix);

        int discountPriceA_rate = discountService.getDiscount(userA, 10000, "rateDiscountPolicyMultiBeanUse");
        System.out.println("discountPriceA_rate = " + discountPriceA_rate);

        int discountPriceB_fix = discountService.getDiscount(userB, 10000, "fixDiscountPolicyMultiBeanUse");
        System.out.println("discountPriceB_fix = " + discountPriceB_fix);

        int discountPriceB_rate = discountService.getDiscount(userB, 10000, "rateDiscountPolicyMultiBeanUse");
        System.out.println("discountPriceB_rate = " + discountPriceB_rate);
    }

    @Test
    @DisplayName("DiscountPolicy2Config로 동일한 타입의 다중 빈 다루기")
    void findEveryBean2() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService2Config.class, DiscountService2.class);

        DiscountService2 discountService = ac.getBean(DiscountService2.class);
        Member userA = new Member(1L, "userA", Grade.VIP);
        Member userB = new Member(2L, "userB", Grade.BASIC);

        int discountPriceA_fix = discountService.getDiscount(userA, 10000, "fixDiscountPolicy");
        System.out.println("discountPriceA_fix = " + discountPriceA_fix);

        int discountPriceA_rate = discountService.getDiscount(userA, 10000, "rateDiscountPolicy");
        System.out.println("discountPriceA_rate = " + discountPriceA_rate);

        int discountPriceB_fix = discountService.getDiscount(userB, 10000, "fixDiscountPolicy");
        System.out.println("discountPriceB_fix = " + discountPriceB_fix);

        int discountPriceB_rate = discountService.getDiscount(userB, 10000, "rateDiscountPolicy");
        System.out.println("discountPriceB_rate = " + discountPriceB_rate);
    }
}
