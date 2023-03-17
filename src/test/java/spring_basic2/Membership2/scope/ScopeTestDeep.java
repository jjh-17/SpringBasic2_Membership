package spring_basic2.Membership2.scope;

import ch.qos.logback.core.net.server.Client;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;


public class ScopeTestDeep {
    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count += 1;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy " + this);
        }
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {
        private final PrototypeBean prototypeBean; //생성 시점에 의존관계 주입

        public int logic() {
            System.out.println("prototypeBean = " + prototypeBean);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    //provider를 이용하여 클라이언트마다 각기 다은 프로토타입 빈을 가지게 함
    @Scope("singleton")
    static class ClientBean2 {
        //Provider : 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 기능 제공
        private final ObjectProvider<PrototypeBean> provider;

        @Autowired
        public ClientBean2(ObjectProvider<PrototypeBean> provider) {
            this.provider = provider;
        }

        public int logic() {
            PrototypeBean prototypeBean = provider.getObject();
            System.out.println("prototypeBean = " + prototypeBean);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    //JSR-330 Provider를 이용하여 클라이언트마다 각기 다은 프로토타입 빈을 가지게 함
    @Scope("singleton")
    static class ClientBean3 {
        //Provider : 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 기능 제공
        private final Provider<PrototypeBean> provider;

        @Autowired
        public ClientBean3(Provider<PrototypeBean> provider) {
            this.provider = provider;
        }

        public int logic() {
            PrototypeBean prototypeBean = provider.get();
            System.out.println("prototypeBean = " + prototypeBean);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    //각 빈은 서로 다른 인스턴스를 가지므로
    @Test
    @DisplayName("스프링 컨테이너에 프로토타입 빈을 직접 요청")
    void requestPrototypeFromContainerTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        bean1.addCount();
        assertThat(bean1.getCount()).isEqualTo(1);
        System.out.println("bean1.getCount() = " + bean1.getCount() + "\n");

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        assertThat(bean2.getCount()).isEqualTo(1);
        System.out.println("bean2.getCount() = " + bean2.getCount());
    }

    @Test
    @DisplayName("싱글톤 클라이언트가 프로토타입 빈을 사용하는 경우")
    void sigletonClientUsePrototypeBean() {
        //Prototype, ClientBean 모두 ComponentScan
        //ClientBean(싱글톤)은 생성 시점에만 의존관계를 주입 받으므로, PrototypeBean도 새로 생성은 되나 그 값이 일정하게 유지됨
        //다른 싱글톤 빈이 동일한 프로토타입 빈을 주입받으면 주입 받는 시점에 각각 새로운 프로토타입 빈이 생성됨
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic(); //주입 완료된 PrototypeBean을 대상으로 한 logic
        System.out.println("count1 = " + count1 + "\n");
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        System.out.println("count2 = " + count2);
        assertThat(count2).isEqualTo(2);
    }

    @Test
    @DisplayName("Providier로 싱글톤 클라이언트가 프로토타입 빈을 사용하는 경우")
    void sigletonClientUsePrototypeBean2() {
        //
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);

        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic(); //주입 완료된 PrototypeBean을 대상으로 한 logic
        System.out.println("count1 = " + count1 + "\n");
        assertThat(count1).isEqualTo(1);

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        System.out.println("count2 = " + count2);
        assertThat(count2).isEqualTo(1);
    }

    @Test
    @DisplayName("JSR-330 Providier로 싱글톤 클라이언트가 프로토타입 빈을 사용하는 경우")
    void sigletonClientUsePrototypeBean3() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);

        ClientBean3 clientBean1 = ac.getBean(ClientBean3.class);
        int count1 = clientBean1.logic(); //주입 완료된 PrototypeBean을 대상으로 한 logic
        System.out.println("count1 = " + count1 + "\n");
        assertThat(count1).isEqualTo(1);

        ClientBean3 ClientBean3 = ac.getBean(ClientBean3.class);
        int count2 = ClientBean3.logic();
        System.out.println("count2 = " + count2);
        assertThat(count2).isEqualTo(1);
    }
}
