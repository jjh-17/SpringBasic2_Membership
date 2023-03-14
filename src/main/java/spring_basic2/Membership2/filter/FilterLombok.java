package spring_basic2.Membership2.filter;

import java.lang.annotation.*;

//Lombok에 사용할 필터
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FilterLombok {
}
