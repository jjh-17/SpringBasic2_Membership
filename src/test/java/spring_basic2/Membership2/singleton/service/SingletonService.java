package spring_basic2.Membership2.singleton.service;

/*
[싱글톤 패턴]
클래스의 인스턴스가 단 1번만 생성되도록 보장
    ==> 객체 인스턴스를 2개 이상 생성하지 못하도록 방지 필요

[단점]
1. 싱글톤 패턴 구현을 위한 코드 필요
2. 클라이언트가 구체 클래스에 의존 ==> DIP 위반
        EX) SingletonService s1 = SingletonService.getInstance();
3. 클라이언트가 구체 클래스의 의존 ==> OCP 위반 가능성 높음
4. 싱글톤 클래스 내에 인스턴스를 미리 생성 ==> 설정이 이미 종료됨 ==> 유연한 테스트 어려움
5. private 생성자에 의해 자식 클래스 만들기 어려움, 내부 속성 변경*초기화 어려움
    ==> 유연한 코딩이 어려움

위 단점을 극복한 SingletonContainer
    ==> AppConFigSpring
 */
public class SingletonService {
    //static 영영에 자기 자신을 상수로써 생성 ==> 공유할 객체를 미리 생성하여 공유
    private static final SingletonService instance = new SingletonService();

    //생성자를 private으로 선언 ==> 외부에서의 new를 사용한 인스턴스 생성 방지
    private SingletonService() { }

    //static 영역에 인스턴스 반환 메서드 정의 ==> 항상 동일한 인스턴스를 반환하도록 한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //실제 로직
    public void logic() {
        System.out.println("call SingletonService.logic");
    }
}
