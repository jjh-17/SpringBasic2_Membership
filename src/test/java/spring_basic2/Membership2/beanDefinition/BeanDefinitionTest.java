package spring_basic2.Membership2.beanDefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import spring_basic2.Membership2.AppConfigSpring;

public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);
    GenericXmlApplicationContext ac2 = new GenericXmlApplicationContext("appConfigSpring.xml");

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean(){
        String[] bdNames = ac.getBeanDefinitionNames();

        System.out.println("[빈 설정 메타 정보 확인]");
        for (String bdName : bdNames) {
            BeanDefinition bd = ac.getBeanDefinition(bdName);

            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + bdName
                        + " / beanDefinition = " + bd);
            }
        }
    }

    @Test
    @DisplayName("빈 설정 메타 정보 확인 - XML")
    void findApplicationBean2(){
        String[] bdNames = ac2.getBeanDefinitionNames();

        System.out.println("[빈 설정 메타 정보 확인 - XML]");
        for (String bdName : bdNames) {
            BeanDefinition bd = ac2.getBeanDefinition(bdName);

            if (bd.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + bdName
                        + " / beanDefinition = " + bd);
            }
        }
    }
}
