package spring_basic2.Membership2.componentScan.filter;

import java.lang.annotation.*;

//ComponentScan에서 제거할 Annotation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComponentExclude {

}
