package spring_basic2.Membership2.singleton.service;

//stateful한 price 변수 제거
public class StatelessService {
    public int order(String name, int price) {
        System.out.println("name = " + name + " / price = " + price);
        return price;
    }
}
