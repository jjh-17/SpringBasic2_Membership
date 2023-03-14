package spring_basic2.Membership2.configuration;

/*
[컴포넌트 스캔]
-설정 정보 없이 자동으로 스프링 빈 등록
-@Bean없이 @Component가 붙은 모든 클래스를 스프링 빈으로 등록
-기본적으로 클래스명의 맨 앞글자를 소문자로 바꾸어 등록하나, @Component("이름")으로 빈 이름 직접 지정 가능

-기본 구조 예상도는 아래와 같다
   <빈 이름>                             <빈 객체>
1. memberServiceImplComponent           MemberServiceImplComponent@x01
2. orderServiceImplComponent            OrderServiceImplComponent@x02
3. momoryMemberRepositoryComponent      MomoryMemberRepositoryComponent@x03
4. rateDiscountPolicyComponent          RateDiscountPolicyComponent@x04

[@Autowired]
-생성자에 @Autowired 지정 시, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다. 생성자에 파라미터가 많아도 찾아서 자동 주입
-기본적으로 같은 타입의 빈을 찾아서 주입 ==> 동일한 타입이 여러가지 있을 경우 에러 발생 가능

[컴포넌트 스캔 기본 대상]
                  <사용처>                 <스프링 부가 기능>
-@Component     : 컴포넌트 스캔
-@Controlller   : 스프링 MVC 컨트롤러       스프링 MVC 컨트롤러로 인식
-@Service       : 스프링 비즈니스 로직       X
-@Repository    : 스프링 데이터 접근 계층    스프링 데이터 접근 계층으로 인식==>데이터 계층의 예외를 스프링 예외로 변환
-@Configuration : 스프링 설정 정보          스프링 빈이 싱글톤을 유지하도록 추가 처리

[필터 타입 - 매우 드믈게 사용. 그냥 있다는 것만 알아둘 것]
-Annotation : 기본값. Annotation 인식
-ASSIGNABLE_TYPE : 지정 타입 + 자식 타입 인식
-ASPECTJ : AspectK 패턴 이용
-REGEX : 정규 표현식
-CUSTOM : TypeFilter라는 인터페이스 구현, 처리
 */

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
                classes = {Configuration.class, FilterLombok.class, FilterFieldMatch.class,
                        FilterQualifier.class, FilterQualifier2.class, FilterPrimary.class}),
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = FilterAutoDI.class)
)
public class AppConfigAutoDI {
}
