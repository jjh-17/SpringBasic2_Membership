package spring_basic2.Membership2.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Configuration
    static class LifeCycleConfig {
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

    //인터페이스를 이용한 생명주기 콜백 Config
    @Configuration
    static class LifeCycleConfig2 {
        @Bean
        public NetworkClientWithInterface networkClientWithInterface() {
            NetworkClientWithInterface networkClientWithInterface = new NetworkClientWithInterface();
            networkClientWithInterface.setUrl("http://hello-spring.dev");
            return networkClientWithInterface;
        }
    }

    //초기화, 소멸 메서드 지정을 이용한 생명주기 콜백 Config
    @Configuration
    static class LifeCycleConfig3 {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClientWithConfig networkClientWithConfig() {
            NetworkClientWithConfig networkClientWithConfig = new NetworkClientWithConfig();
            networkClientWithConfig.setUrl("http://hello-spring.dev");
            return networkClientWithConfig;
        }
    }

    //어노테이션을 이용한 생명주기 콜백 Config
    @Configuration
    static class LifeCycleConfig4 {
        @Bean
        public NetworkClientWithAnnotation networkClientWithAnnotation() {
            NetworkClientWithAnnotation networkClientWithAnnotation = new NetworkClientWithAnnotation();
            networkClientWithAnnotation.setUrl("http://hello-spring.dev");
            return networkClientWithAnnotation;
        }
    }

    @Test
    @DisplayName("객체 생성, 의존관계 주입 후 초기화 작업")
    public void lifeCycleTest() {
        //url 설정 전에 생성자가 호출되기 때문에 url은 null
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Test
    @DisplayName("Interface를 이용하여 객체 생성, 의존관계 주입 이전 초기화 작업")
    public void lifeCycleTest2() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig2.class);
        NetworkClientWithInterface networkClientWithInterface = ac.getBean(NetworkClientWithInterface.class);
        ac.close();
    }

    @Test
    @DisplayName("초기화, 소멸 메서드를 지정하여 객체 생성, 의존관계 주입 이전 초기화 작업")
    public void lifeCycleTest3() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig3.class);
        NetworkClientWithConfig networkClientWithConfig = ac.getBean(NetworkClientWithConfig.class);
        ac.close();
    }

    @Test
    @DisplayName("어노테이션을 이용하여 객체 생성, 의존관계 주입 이전 초기화 작업")
    public void lifeCycleTest4() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig4.class);
        NetworkClientWithAnnotation networkClientWithAnnotation = ac.getBean(NetworkClientWithAnnotation.class);
        ac.close();
    }
}
