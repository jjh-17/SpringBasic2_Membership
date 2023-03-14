package spring_basic2.Membership2.filter;

import org.springframework.beans.factory.annotation.Qualifier;
import java.lang.annotation.*;


//임의의 Qualifier가 선언된 유사 Qualifier를 선언
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
