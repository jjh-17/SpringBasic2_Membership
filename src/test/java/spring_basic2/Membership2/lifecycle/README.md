[스프링 빈 생명주기 테스트]
스프링의 초기화 작업과 죵료 작업 진행 과정을 테스트

# 스프링 빈의 LifeCycle
객체 생성 ==> 의존관계 주입

# 스프링 빈의 이벤트 LifeCycle
스프링 컨테이너 생성 ==> 스프링 빈 생성 ==> 의존관계 주입 ==> 초기화 콜백
    ==> 사용 ==> 소멸전 콜백 ==> 스프링 종료

# 객체 생성, 초기화는 분리해야 한다
생성자 : 파라미터를 받고 메모리를 할당하여 객체 생성
초기화 : 생성한 객체를 활용하여 외부 커넥션 연결 등 무거운 작업 수행
    ==> 생성자 내에서 무거운 작업을 수행하는 것보다는 둘을 분리하는 것이 좋음

# 스프링이 지원하는 3가지 빈 생명주기 콜백 방법
1. 인터페이스 : InitializingBean, DisposableBean
2. Config 파일에 초기화 메서드, 종료 메서드 지정
3. Annotation : @PostConstruct, @PreDestroy
