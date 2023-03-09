package spring_basic2.Membership2.order.service;

import spring_basic2.Membership2.order.Order;

public interface OrderService {
    Order createOrder(Long memberId, String productName, int productPrice);
}
