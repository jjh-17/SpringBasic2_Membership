package spring_basic2.Membership2.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.AppConfigSpring;
import spring_basic2.Membership2.member.service.MemberService;
import spring_basic2.Membership2.singleton.service.SingletonService;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfigSpring appConfig = new AppConfigSpring();

        //순수 DI 컨테이너는 클라이언트가 요청할 때마다 새로운 객체를 생성함
        //즉, 트래픽과 객체 생성*소멸이 정비례하여 메모리 소모가 큼
        //SingletonService : 클래스 인스턴스가 단 1개만 생성되도록 보장하는 싱글톤 패턴이 적용된 클래스
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다름
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonTest(){
        //사용 불가
        //SingletonService s = new SingletonService();

        //given
        SingletonService s1 = SingletonService.getInstance();
        SingletonService s2 = SingletonService.getInstance();

        //then
        System.out.println("singletonService1 = " + s1);
        System.out.println("singletonService2 = " + s2);

        //isSameAs : 주소값을 비교
        //isEqualTo : 값, 혹은 객체의 참조 비교
        assertThat(s1).isSameAs(s2);
        assertThat(s1).isEqualTo(s2);
    }

    @Test
    @DisplayName("싱글톤 컨테이너")
    void singletonContainerTest(){
        //@Configuration, @Bean을 등록한 싱글톤 컨테이너
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        //클라이언트가 호출할 때 마다 동일한 객체 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
        assertThat(memberService1).isEqualTo(memberService2);
    }
}
