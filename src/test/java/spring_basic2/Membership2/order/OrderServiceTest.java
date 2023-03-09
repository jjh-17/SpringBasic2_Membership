package spring_basic2.Membership2.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring_basic2.Membership2.AppDIPConfig;
import spring_basic2.Membership2.AppDIPConfigRefactored;
import spring_basic2.Membership2.enumeration.Grade;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.MemberService;
import spring_basic2.Membership2.member.MemberServiceImpl;
import spring_basic2.Membership2.member.MemberServiceImplDIP;

public class OrderServiceTest {

    //변수
    MemberService memberService = new MemberServiceImpl();
    MemberService memberServiceDIP;
    MemberService memberServiceDIPRefactored;

    OrderService orderService = new OrderServiceImpl();
    OrderService orderServiceDIP;
    OrderService orderServiceDIPRefactored;

    //초기화
    @BeforeEach
    public void beforeEach(){
        AppDIPConfig appDIPConfig = new AppDIPConfig();
        AppDIPConfigRefactored appDIPConfigRefactored = new AppDIPConfigRefactored();

        memberServiceDIP = appDIPConfig.memberService();
        memberServiceDIPRefactored = appDIPConfigRefactored.memberService();

        orderServiceDIP = appDIPConfig.orderService();
        orderServiceDIPRefactored = appDIPConfigRefactored.orderService();
    }

    //테스트
    @Test
    @DisplayName("OrderService 테스트")
    void createOrder(){
        //given
        long vipId = 1L, basicId = 2L;
        Member memberVIP = new Member(vipId, "VIP", Grade.VIP);
        Member memberBASIC = new Member(basicId, "BASIC", Grade.BASIC);

        //when
        memberService.join(memberVIP);
        memberService.join(memberBASIC);

        Order orderVIP = orderService.createOrder(vipId, "VIP Product", 10000);
        Order orderBASIC = orderService.createOrder(basicId, "BASIC Product", 10000);

        //then
        Assertions.assertThat(orderVIP.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(orderBASIC.getDiscountPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("AppConfigDIPRefactored를 이용한 OrderService 테스트")
    void createOrder2(){
        //given
        long vipId = 1L, basicId = 2L;
        Member memberVIP = new Member(vipId, "VIP", Grade.VIP);
        Member memberBASIC = new Member(basicId, "BASIC", Grade.BASIC);

        //when
        memberServiceDIP.join(memberVIP);
        memberServiceDIP.join(memberBASIC);

        Order orderVIP = orderServiceDIP.createOrder(vipId, "VIP Product", 10000);
        Order orderBASIC = orderServiceDIP.createOrder(basicId, "BASIC Product", 10000);

        //then
        Assertions.assertThat(orderVIP.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(orderBASIC.getDiscountPrice()).isEqualTo(0);
    }

    @Test
    @DisplayName("AppConfigDIPRefactored를 이용한 OrderService 테스트")
    void createOrder3(){
        //given
        long vipId = 1L, basicId = 2L;
        Member memberVIP = new Member(vipId, "VIP", Grade.VIP);
        Member memberBASIC = new Member(basicId, "BASIC", Grade.BASIC);

        //when
        memberServiceDIPRefactored.join(memberVIP);
        memberServiceDIPRefactored.join(memberBASIC);

        Order orderVIP = orderServiceDIPRefactored.createOrder(vipId, "VIP Product", 10000);
        Order orderBASIC = orderServiceDIPRefactored.createOrder(basicId, "BASIC Product", 10000);

        //then
        Assertions.assertThat(orderVIP.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(orderBASIC.getDiscountPrice()).isEqualTo(0);
    }
}
