package spring_basic2.Membership2.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring_basic2.Membership2.filter.FilterFieldMatch;
import spring_basic2.Membership2.filter.*;

@Configuration
@ComponentScan(
        basePackages = "spring_basic2.Membership2",

        //컴포넌트 스캔에서 제외할 대상 지정
        //@Configuration은 @Component를 포함하기 때문에, 예제 코드의 보존을 위해 @Configuration 제외
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = {Configuration.class, FilterAutoDI.class, FilterFieldMatch.class, FilterMultiBeanUse.class,
                        FilterQualifier.class, FilterQualifier2.class, FilterPrimary.class}),

        //스캔에 추가할 대상 지정
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = FilterLombok.class)
)
public class AppConfigLombok {
}
