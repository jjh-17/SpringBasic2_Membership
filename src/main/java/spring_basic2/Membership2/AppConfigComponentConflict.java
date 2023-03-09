package spring_basic2.Membership2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.repository.MemoryMemberRepository;

@Configuration
@ComponentScan(
        basePackages = "spring_basic2.Membership2",

        //컴포넌트 스캔에서 제외할 대상 지정
        //@Configuration은 @Component를 포함하기 때문에, 예제 코드의 보존을 위해 @Configuration 제외
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class)
)
public class AppConfigComponentConflict {
    //자동 등록된 빈과 동일한 이름으로 빈 수동 등록 ==> 수동 등록이 우선권을 가진다.
    @Bean(name = "memoryMemberRepositoryComponent")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
