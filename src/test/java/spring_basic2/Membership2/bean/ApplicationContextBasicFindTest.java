package spring_basic2.Membership2.bean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.configuration.AppConfigSpring;
import spring_basic2.Membership2.member.service.MemberService;
import spring_basic2.Membership2.member.service.MemberServiceImplDIP;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

    @Test
    @DisplayName("빈 이름과 타입으로 조회 - 인터페이스")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImplDIP.class);

        System.out.println("[빈 이름과 타입으로 조회 - 인터페이스]");
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("빈 이름과 타입으로 조회 - 구현체")
    void findBeanByName2() {
        MemberServiceImplDIP memberServiceImpl = ac.getBean("memberService", MemberServiceImplDIP.class);
        assertThat(memberServiceImpl).isInstanceOf(MemberServiceImplDIP.class);

        System.out.println("[빈 이름과 타입으로 조회 - 구현체]");
        System.out.println("memberServiceImplDIP = " + memberServiceImpl);
        System.out.println("memberServiceImplDIP.getClass() = " + memberServiceImpl.getClass());
    }

    @Test
    @DisplayName("빈 이름과 타입으로 조회 실패")
    void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImplDIP.class);

        System.out.println("[이름 없이 타입으로만 조회]");
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회2")
    void findBeanByType2() {
        MemberService memberServiceImplDIP = ac.getBean(MemberServiceImplDIP.class);
        assertThat(memberServiceImplDIP).isInstanceOf(MemberServiceImplDIP.class);

        System.out.println("[이름 없이 타입으로만 조회2]");
        System.out.println("memberServiceImpl = " + memberServiceImplDIP);
        System.out.println("memberServiceImpl.getClass() = " + memberServiceImplDIP.getClass());
    }
}
