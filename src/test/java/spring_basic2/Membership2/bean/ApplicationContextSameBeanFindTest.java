package spring_basic2.Membership2.bean;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_basic2.Membership2.member.MemberRepository;
import spring_basic2.Membership2.member.MemoryMemberRepository;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    //동일한 타입을 가지는 스프링 빈 등록
    @Configuration
    static class SameBeanConfig { //이 클래스 내에서만 사용할 inner class
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("임시 Configuration에서 동일한 타입을 가진 빈을 타입으로 조회")
    void printDuplicateBeanByType() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("임시 Configuration에서 동일한 타입을 가진 빈을 빈 이름과 타입으로 조회")
    void printDuplicateBeanByType2() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        MemberRepository memberRepository2 = ac.getBean("memberRepository2", MemberRepository.class);

        Assertions.assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
        Assertions.assertThat(memberRepository2).isInstanceOf(MemberRepository.class);

        System.out.println("[동일한 타입을 가진 빈을 빈 이름과 타입으로 조회]");
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository1.getClass() = " + memberRepository1.getClass());
        System.out.println("\nmemberRepository2 = " + memberRepository2);
        System.out.println("memberRepository2.getClass() = " + memberRepository2.getClass());
    }

    @Test
    @DisplayName("임시 Configuration에서 특정 타입의 빈 모두 출력")
    void printEveryBeanByType(){
        Map<String, MemberRepository> beanTypeMap = ac.getBeansOfType(MemberRepository.class);

        System.out.println("[특정 타입의 빈 모두 출력]");
        Assertions.assertThat(beanTypeMap.size()).isEqualTo(2);

        for (String key : beanTypeMap.keySet()) {
            System.out.println("key = " + key + "\nvalue = " + beanTypeMap.get(key) + "\n");
        }
        System.out.println("beanTypeMap = " + beanTypeMap);

    }
}
