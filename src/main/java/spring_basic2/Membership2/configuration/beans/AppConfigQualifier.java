package spring_basic2.Membership2.configuration.beans;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring_basic2.Membership2.filter.*;

@Configuration
@ComponentScan(
        /*
        탐색 패키지 시작 위치 지정 ==> 시간 단축, 잘못된 패키지 참조 방지 가능.
        한번에 여러 위치 지정 가능 ==> basePackages = {"a.123", "b.31"}
        지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨
        basePackageClasses : 지정 클래스의 패키지를 탐색 시작 위치로 지정
        가장 권장되는 방법은 설정 정보 클래스를 프로젝트 최상단에 두는 것.
         */
        basePackages = "spring_basic2.Membership2",

        //컴포넌트 스캔에서 제외할 대상 지정
        //@Configuration은 @Component를 포함하기 때문에, 예제 코드의 보존을 위해 @Configuration 제외
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = {Configuration.class, FilterLombok.class, FilterFieldMatch.class, FilterMultiBeanUse.class,
                        FilterAutoDI.class, FilterPrimary.class, FilterQualifier2.class}),
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = FilterQualifier.class)
)
public class AppConfigQualifier {
}
