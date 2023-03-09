package spring_basic2.Membership2.bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic2.Membership2.discount.DiscountPolicy;
import spring_basic2.Membership2.discount.FixDiscountPolicy;
import spring_basic2.Membership2.discount.RateDiscountPolicy;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상이면 중복 오류 발생")
    void findBeanByParentType() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("빈 이름으로 부모 타입 조회")
    void findBeanByParentTypeBeanName() {
        //given
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        DiscountPolicy fixDiscountPolicy = ac.getBean("fixDiscountPolicy", DiscountPolicy.class);

        //when

        //then
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
        Assertions.assertThat(fixDiscountPolicy).isInstanceOf(FixDiscountPolicy.class);

        System.out.println("[빈 이름으로 부모 타입 조회]");
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
        System.out.println("rateDiscountPolicy.getClass() = " + rateDiscountPolicy.getClass());
        System.out.println("\nfixDiscountPolicy = " + fixDiscountPolicy);
        System.out.println("rateDiscountPolicy.getClass() = " + rateDiscountPolicy.getClass());
    }

    @Test
    @DisplayName("특정 하위 타입으로 빈 조회")
    void findBeanBySubType() {
        //given
        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        FixDiscountPolicy fixDiscountPolicy = ac.getBean(FixDiscountPolicy.class);

        //then
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);

        System.out.println("[특정 하위 타입으로 빈 조회]");
        System.out.println("rateDiscountPolicy = " + rateDiscountPolicy);
        System.out.println("rateDiscountPolicy.getClass() = " + rateDiscountPolicy.getClass());
        System.out.println("\nfixDiscountPolicy = " + fixDiscountPolicy);
        System.out.println("rateDiscountPolicy.getClass() = " + rateDiscountPolicy.getClass());
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void printEveryBeanByParentType(){
        Map<String, DiscountPolicy> beanTypeMap = ac.getBeansOfType(DiscountPolicy.class);

        System.out.println("[부모 타입으로 모두 조회]");
        Assertions.assertThat(beanTypeMap.size()).isEqualTo(2);

        for (String key : beanTypeMap.keySet()) {
            System.out.println("key = " + key + "\nvalue = " + beanTypeMap.get(key) + "\n");
        }
        System.out.println("beanTypeMap = " + beanTypeMap);
    }

    @Test
    @DisplayName("Object 타입으로 모두 조회")
    void printEveryBeanByObjectType() {
        //스프링 빈에 등록된 모든 타입 조회
        Map<String, Object> beanTypeMap = ac.getBeansOfType(Object.class);

        System.out.println("[Object 타입으로 모두 조회]");
        for (String key : beanTypeMap.keySet()) {
            System.out.println("key = " + key + "\nvalue = " + beanTypeMap.get(key) + "\n");
        }
    }

}
