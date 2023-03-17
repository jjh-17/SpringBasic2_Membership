package spring_basic2.Membership2.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/*
[어노테이션]
-가장 권장되는 방법
-메서드 이름 지정 가능
-매우 편리하며, Component Scan과 잘 어울림
-외부 라이브러리에 적용 불가 ==> @Bean을 이용하여 외부 라이브러리 초기화, 종료 가능
 */
public class NetworkClientWithAnnotation {
    //접속할 서버 url
    private String url;

    public NetworkClientWithAnnotation() {
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
    @PostConstruct
    public void init(){
        System.out.println("NetworkClientWithAnnotation.init");
        connect();
        call("초기화 연결 메시지");
    }

    //스프링 종료 직전 호출]
    @PreDestroy
    public void close() {
        System.out.println("NetworkClientWithAnnotation.close");
        disconnect();
    }
}
