package spring_basic2.Membership2.lifecycle;

public class NetworkClient {
    //접속할 서버 url
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
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
}
