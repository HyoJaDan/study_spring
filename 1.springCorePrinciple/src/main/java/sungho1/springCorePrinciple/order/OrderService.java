package sungho1.springCorePrinciple.order;

public interface OrderService {
    Order createOrder(Long memberId,String itemName, int itemPrice);
}
