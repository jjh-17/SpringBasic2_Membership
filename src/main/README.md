스프링을 사용하지 않은 순수 자바 코드로 회원 멤버십 기능 구축

[회원]
1. 회원 가입 및 조회
2. 회원 종류 - 일반, VIP
3. 외부 시스템과 연동 가능한 자체 DB를 구축하여 회원 데이터 저장(변동 가능)

[주문과 할인 정책]
1. 회원은 상품 주문 가능
2. 현재 VIP 회원은 모든 상품에 대하여 1000원 할인을 적용(변동 가능)

=======================================================================================

[적용된 좋은 객체 지향 설계 3가지 원칙]
1. SRP - 단일 책임 원칙
   -한 클래스는 하나의 책임만 가져야한다.
   -클래스를 클라이언트 객체와 구현 객체로 나눈다.
   -클라이언트 객체는 구현 객체를 실행한다.
   -구현 객체엔 각 기능이 구현되어야 하며, AppConfig에 의해 생성/연결된다.

2. DIP - 의존관계 역전 원칙
   -구체화가 아닌 추상화에 의존해야한다.
   -클라이언트 코드는 추상화 인터페이스에만 의존을 해야한다.
   -AppConfig가 구현 객체 인스턴스를 생성하여 클라이언트 코드에 의존관계를 주입함으로써 클라이언트 코드가 구현 객체를 실행할 수 있도록 함
-

3. OCP
   -확장에는 열려 있으나, 변경에는 닫혀 있어야 한다.
   -단순히 AppConfig를 수정함으로써 클라이언트 코드의 의존 관계를 변경할 수 있다.
   -소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있음


[IOC - 제어의 역전]
프로그램의 제어 흐름을 외부에서 관리하는 것
AppConfig가 OrderServiceImple, MemberServiceImpl 등의 제어 흐름을 관리


[프레임워크 vs 라이브러리]
프레임워크 : 작성한 코드 제어 및 실행
라이브러리 : 작성한 코드가 직접 제어의 흐름을 담당

[DI - 의존관계 주입]
<정적 클래스 의존관계>
클래스 내 import 코드만으로 의존관계 판단 가능
OrderServiceImpl은 DiscountPolicy, MemberRepository, Member에 의존함 판단 가능

===================================================================================

[스프링 컨테이너 구조]
interface - BeanFactory
|
interface - ApplicationContext
|
AnnotationConfig - ApplicationContext


[BeanFactory]
-스프링 컨테이너의 최상위 인터페이스
-스프링 빈 관리 및 조회
-getBean() 제공

[ApplicationContext]
-BeanFactory 기능을 모두 상속받아 제공
-빈 관리/조회 기능 외에 다른 부가기능 제공
   ==> EX) MassageSource, EnvironmentCapable, ApplicationEventPublisher, ResourceLoader


[스프링 컨테이너의 설정 형식]
<어노테이션 기반 자바 코드>
new AnnotationConfigApplicationContext(AppConfig.class)

<XML>
GenericXmlApplicationContext를 사용하여 xml 설정 파일을 넘긴다.


[스프링 빈 설정 메타 정보 - BeanDefinition]