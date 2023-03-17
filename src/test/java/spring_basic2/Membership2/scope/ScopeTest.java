package spring_basic2.Membership2.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import static org.assertj.core.api.Assertions.*;

public class ScopeTest {
    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy " + this);
        }
    }


    //프로토타입 스코프는 객체 생성, 의존관계 주입, 초기화까지만 관여한다. ==> destroy는 자동으로 호출되지 않고, 수동으로 호촐해야 한다.
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }
    }

    //싱글톤 스코프는 항상 동일한 인스턴스의 스프링 빈 반환
    @Test
    @DisplayName("싱글톤 빈 스코프 확인")
    void singletonBeanFind() {
        //given
        //Config를 안해도 바로 클래스를 등록함으로써 Component Scan 수행 가능
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        //when
        //
        System.out.println("bean1 찾기");
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        System.out.println("bean2 찾기");
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        //then
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isSameAs(bean2);
        ac.close();
    }

    //프로토타입 빈은 항상 다른 인스턴스의 스프링 빈 반환
    @Test
    @DisplayName("프로토타입 빈 스코프 확인")
    void prototypeBeanFind() {
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        //when
        System.out.println("bean1 찾기");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("bean2 찾기");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        //then
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isNotSameAs(bean2);

        bean1.destroy();
        bean2.destroy();

        ac.close();
    }



}
