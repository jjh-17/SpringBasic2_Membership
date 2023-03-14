package spring_basic2.Membership2.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.configuration.AppConfigSpring;
import spring_basic2.Membership2.configuration.AppConfigSpring2;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.service.MemberServiceImplDIP;
import spring_basic2.Membership2.order.service.OrderServiceImplDIP;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    /*
    AppConfigSpring애서 'memberRepository'가 두 번 이상 선언되는 등 Singleton을 해칠 것으로 보이나, 실제로는 한 번만 선언됨
    그 이유는 무엇인가?
     */
    @Test
    @DisplayName("AppConfigSpring Singleton 테스트")
    void configurationSingletonTest() {
        //when
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);
        MemberServiceImplDIP memberService = ac.getBean("memberService", MemberServiceImplDIP.class);
        OrderServiceImplDIP orderService = ac.getBean("orderService", OrderServiceImplDIP.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //then
        //모두 동일한 인스턴스 참조.
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("AppConfigSpring2 Singleton 테스트")
    void configurationSingletonTest2() {
        //when
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring2.class);
        MemberServiceImplDIP memberService = ac.getBean("memberService", MemberServiceImplDIP.class);
        OrderServiceImplDIP orderService = ac.getBean("orderService", OrderServiceImplDIP.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //then
        //모두 동일한 인스턴스 참조.
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isNotSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isNotSameAs(memberRepository);
    }

    @Test
    @DisplayName("AppConfigSpring의 빈 정보 확인으로 Singleton 유지 여부 테스트")
    void configurationDeepTest() {
        //when
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        //then
        //AppConfig의 스프링 빈 등록 정보 조회 결과, AppConfig가 아니라 '~CGLIB'가 붙은 정보가 출력됨
        //이는 AppConfig의 자식 클래스로, 스프링이 바이트코드 조작 라이브러리를 사용해서 싱글톤을 만족하도록 생성한 클래스
        AppConfigSpring bean = ac.getBean(AppConfigSpring.class);
        System.out.println("bean = " + bean.getClass());
    }

    @Test
    @DisplayName("@Configuration을 제거한 AppConfigSpring2의 빈 정보 확인으로 Singleton 유지 여부 테스트")
    void configurationDeepTest2() {
        //when
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring2.class);

        //then
        //AppConfig2의 스프링 빈 등록 정보 조회 결과 AppConfig2가 동록되었으나, Singleton을 만족하지 않음
        AppConfigSpring2 bean = ac.getBean(AppConfigSpring2.class);
        System.out.println("bean = " + bean.getClass());
    }
}
