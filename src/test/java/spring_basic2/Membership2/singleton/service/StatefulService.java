package spring_basic2.Membership2.singleton.service;

//상태를 유지하는 - stateful한 필드가 존재하는 클래스
public class StatefulService {
    //상태를 유지하는 필드
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " / price = " + price);
        this.price = price; //문제 발생 ==> 동일한 인스턴스를 공유하는 두 클라이언트가 price에 접근 가능
    }

    public int getPrice() {
        return price;
    }
}