package spring_basic2.Membership2.filter;

import java.lang.annotation.*;

//빈이 2개 이상일 때 : 두 빈 모두 사용할 때
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FilterMultiBeanUse {
}
