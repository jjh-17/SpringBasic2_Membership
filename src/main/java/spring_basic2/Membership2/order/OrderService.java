package spring_basic2.Membership2.order;

public interface OrderService {
    Order createOrder(Long memberId, String productName, int productPrice);
}
