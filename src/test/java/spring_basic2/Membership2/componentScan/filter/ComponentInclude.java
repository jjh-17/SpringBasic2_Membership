package spring_basic2.Membership2.componentScan.filter;

import java.lang.annotation.*;

//ComponentScan에 추가할 Annotation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComponentInclude {

}
