package spring_basic2.Membership2.componentScan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.AppConfigComponent;
import spring_basic2.Membership2.AppConfigComponentConflict;
import spring_basic2.Membership2.member.service.MemberService;

public class AppConfigComponentTest {
    @Test
    @DisplayName("ComponentScan을 이용한 AppConfigComponent 테스트")
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigComponent.class);

        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

    @Test
    @DisplayName("수동 등록된 빈과 자동 등록된 빈의 충돌 결과 테스트")
    void basicConflictScan() {
        //자동 등록된 빈과 동일한 이름으로 빈을 수동 등록하여 충돌을 유도한 클래스의 빈 등록 정보 불러오기
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigComponentConflict.class);

        //수동 빈이 자동 등록 빈을 오버라이딩 한다. ==> 대부분 버그
        //최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌할 경우 오류가 발생하도록 함
        //      ==> @SpringBootApplication이 선언된 곳을 실행하면 오류 발생
        //application.properties에 'spring.main.allow-bean-definition-overriding=true'를 추가하여 오버라이딩 설정 변경 가능
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
