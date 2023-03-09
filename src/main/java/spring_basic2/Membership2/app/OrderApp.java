package spring_basic2.Membership2.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_basic2.Membership2.AppConfigSpring;
import spring_basic2.Membership2.AppDIPConfig;
import spring_basic2.Membership2.AppDIPConfigRefactored;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.MemberService;
import spring_basic2.Membership2.order.Order;
import spring_basic2.Membership2.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {
        appDIP();
        appDIP2();
        appSpring();
    }

    public static void appDIP() {
        AppDIPConfig appDIPConfig = new AppDIPConfig();
        MemberService memberService = appDIPConfig.memberService();
        OrderService orderService = appDIPConfig.orderService();

        long memberID = 1L;
        Member member = new Member(memberID, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberID, "itemA", 10000);
        System.out.println("[AppDIPConfig]");
        System.out.println("order = " + order + '\n');
    }

    public static void appDIP2() {
        AppDIPConfigRefactored appDIPConfig = new AppDIPConfigRefactored();
        MemberService memberService = appDIPConfig.memberService();
        OrderService orderService = appDIPConfig.orderService();

        long memberID = 1L;
        Member member = new Member(memberID, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberID, "itemA", 10000);
        System.out.println("[AppDIPConfigRefactored]");
        System.out.println("order = " + order + '\n');
    }

    public static void appSpring() {
        //스프링 컨테이너 호출
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfigSpring.class);

        //빈 정보 받기
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(1L, "itemA", 10000);

        System.out.println("[AppConfigSpring]");
        System.out.println("order = " + order);
    }
}
