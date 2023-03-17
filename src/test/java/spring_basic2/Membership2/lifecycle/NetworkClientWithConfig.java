package spring_basic2.Membership2.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
[빈 등록 초기화, 소멸 메서드 지정]
-메서드 이름 지정 가능
-스프링 빈이 스프링 코드에 의존하지 않음
-설정 정보를 이용 ==> 코드 수정이 불가한 외부 라이브러리에도 초기화, 종료 메서드 지정 가능
-종료 메서드의 경우, 따로 등록을 하지 않더라도 추론해서 자동으로 호출한다.
 */
public class NetworkClientWithConfig{
    //접속할 서버 url
    private String url;

    public NetworkClientWithConfig() {
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

    //초기화
    public void init(){
        System.out.println("NetworkClientWithConfig.init");
        connect();
        call("초기화 연결 메시지");
    }

    //스프링 종료 직전 호출
    public void close() {
        System.out.println("NetworkClientWithConfig.close");
        disconnect();
    }
}
