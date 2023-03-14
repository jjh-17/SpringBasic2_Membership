package spring_basic2.Membership2.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.configuration.AppConfigSpring;
import spring_basic2.Membership2.configuration.AppDIPConfig;
import spring_basic2.Membership2.configuration.AppDIPConfigRefactored;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.service.MemberService;

public class MemberApp {
    public static void main(String[] args) {
        appDIP();
        appDIP2();
        appSpring();
    }

    //AppDIPConfig를 이용한 실행
    public static void appDIP() {
        AppDIPConfig appDIPConfig = new AppDIPConfig();
        MemberService memberService = appDIPConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("[AppDIPConfig]");
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName() + '\n');
    }

    //AppDIPConfigRefactored를 이용한 실행
    public static void appDIP2() {
        AppDIPConfigRefactored appDIPConfig = new AppDIPConfigRefactored();
        MemberService memberService = appDIPConfig.memberService();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("[AppDIPConfigRefactored]");
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName() + '\n');
    }

    //AppConfigSpring을 이용한 실행
    public static void appSpring() {
        //스프링 컨테이너 호출
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        //현재 관리하는 컨테이너 내 "memberService"라는 메서드와, 그 메서드의 리턴 타입에 해당하는 빈 정보를 받는다.
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("[AppConfigSpring]");
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName() + '\n');

    }
}
