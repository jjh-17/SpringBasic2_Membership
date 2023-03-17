package spring_basic2.Membership2.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
[인터페이스를 이용한 생명주기 콜백]
-스프링 전용 인터페이스으므로 관련 모든 코드가 스프링 전용 인터페이스에 의존
-초기화, 소멸 메서드의 이름 변경 불가
-수정할 수 없는 외부 라이브러리에 적용 불가

 */
public class NetworkClientWithInterface implements InitializingBean, DisposableBean {
    //접속할 서버 url
    private String url;

    public NetworkClientWithInterface() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        System.out.println("url 설정 : " + url);
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    //url과 연결되었다고 가정하고, message 출력
    public void call(String message) {
        System.out.println("call : " + url + " / message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect : " + url);
    }

    //초기화 ==> 의존관계 주입 이후
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClientWithAnnotation.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    //스프링 종료 직전 호출
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClientWithAnnotation.destroy");
        disconnect();
    }
}
