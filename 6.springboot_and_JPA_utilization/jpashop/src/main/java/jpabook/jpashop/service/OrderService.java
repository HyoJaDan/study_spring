package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.Orders;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item=itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성 ** 여기서 내가 엔티티에서 만들었떤 함수들을 사용한다.
        // 1. orderItem 먼저 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 2. 주문 생성
        Orders order = Orders.createOrder(member, delivery, orderItem);

        // 3. 주문 저장
        // 여기서 orderRepository만 save해도 되는이유는 Orders클래스 보면, orderItems와
        // Delivery가 모두 CascadeTyle.all이 되어있다. 즉 Orders만 나가면 모두 자동으로 데이터가 DB에 들어간다는 뜻이다!
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Orders order= orderRepository.findOne(orderId);
        //  주문 취소
        order.calcel();
    }
    //검색
    public void findOrders(OrderSearch orderSearch) {

    }



}
