package spring_basic2.Membership2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring_basic2.Membership2.filter.FilterFieldMatch;
import spring_basic2.Membership2.filter.*;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.repository.MemoryMemberRepository;

@Configuration
@ComponentScan(
        basePackages = "spring_basic2.Membership2",

        //컴포넌트 스캔에서 제외할 대상 지정
        //@Configuration은 @Component를 포함하기 때문에, 예제 코드의 보존을 위해 @Configuration 제외
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = {Configuration.class, FilterLombok.class, FilterFieldMatch.class, FilterMultiBeanUse.class,
                        FilterQualifier.class, FilterQualifier2.class, FilterPrimary.class}),
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = FilterAutoDI.class)
)
public class AppConfigConflictAutoDI {
    //자동 등록된 빈과 동일한 이름으로 빈 수동 등록 ==> 수동 등록이 우선권을 가진다.
    @Bean(name = "memoryMemberRepositoryAutoDI")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
