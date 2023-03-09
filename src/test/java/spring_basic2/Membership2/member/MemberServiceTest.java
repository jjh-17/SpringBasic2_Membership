package spring_basic2.Membership2.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring_basic2.Membership2.AppDIPConfig;
import spring_basic2.Membership2.AppDIPConfigRefactored;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.service.MemberService;
import spring_basic2.Membership2.member.service.MemberServiceImpl;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
    MemberService memberServiceDIP;
    MemberService memberServiceDIPRefactored;

    @BeforeEach
    public void beforeEach() {
        AppDIPConfig appDIPConfig = new AppDIPConfig();
        AppDIPConfigRefactored appDIPConfigRefactored = new AppDIPConfigRefactored();

        memberServiceDIP = appDIPConfig.memberService();
        memberServiceDIPRefactored = appDIPConfigRefactored.memberService();
    }

    @Test
    @DisplayName("MemberService 테스트")
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    @DisplayName("appDIPConfig를 이용한 MemberService 테스트")
    void join2() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    @DisplayName("appDIPConfigRefactored를 이용한 MemberService 테스트")
    void join3() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
