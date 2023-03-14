package spring_basic2.Membership2.autowired;

import jakarta.annotation.Nullable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.member.Member;

import java.util.Optional;

public class AutoWiredTest {
    //테스트 클래스
    static class TestBean {
//        @Autowired //member로 들어오는 것이 없으므로 오류가 발생한다.
//        public void setNoBean0(Member member) {
//            System.out.println("setNoBean1.member = " + member);
//        }

        @Autowired(required = false) //자동 주입할 대상이 없다면 수정자 메서드 자체가 호출되지 않는다
        public void setNoBean1(Member member) {
            System.out.println("setNoBean1.member = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) { //자동 주입할 대상이 없다면 대신 null을 주입한다.
            //@Nullable : member가 들어오지 않는다면 대신 null을 넣는다.
            System.out.println("setNoBean2.member = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) { //자동 주입 대상이 없다면 Optional.empty를 주입한다.
            System.out.println("setNoBean3.member = " + member);
        }
    }

    @Test
    @DisplayName("옵션 처리 테스트")
    void AutoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
}
