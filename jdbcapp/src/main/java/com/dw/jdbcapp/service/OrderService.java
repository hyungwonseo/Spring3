package com.dw.jdbcapp.service;

import com.dw.jdbcapp.dto.OrderRequestDTO;
import com.dw.jdbcapp.exception.InvalidRequestException;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.model.Product;
import com.dw.jdbcapp.repository.iface.OrderDetailRepository;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import com.dw.jdbcapp.repository.iface.ProductRepository;
import com.dw.jdbcapp.repository.jdbc.OrderJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OrderService {
    @Autowired
    @Qualifier("orderTemplateRepository")
    OrderRepository orderRepository;
    @Autowired
    @Qualifier("orderDetailTemplateRepository")
    OrderDetailRepository orderDetailRepository;
    @Autowired
    @Qualifier("productTemplateRepository")
    ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Order getOrderById(String orderNumber) {
        return orderRepository.getOrderById(orderNumber);
    }

    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        // 과제 3-4 제품번호와 고객번호로 주문정보를 조회할때 데이터가 없는 경우의 예외처리
        List<Order> orders = orderRepository.getOrderByIdAndCustomer(productNumber, customerId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException(
                    "해당되는 정보가 없습니다: " + productNumber + ", "
                            + customerId);
        }
        return orders;
    }

    // @Transactional은 선언된 메서드 수행도중 예외가 발생하면 이미 수행되었던
    // 동작을 모두 롤백(rollback=원상복귀)시키도록 명령하는 어노테이션임
    // 주문세부의 특정 제품의 재고가 부족해서 예외가 발생하면 전체 주문,주문세부의
    // 저장되었던 내용들은 모두 취소되고 롤백됨!!
    @Transactional
    public OrderRequestDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        // 1. DTO에서 주문정보를 꺼내 주문테이블에 insert
        orderRepository.saveOrder(orderRequestDTO.toOrder());
        // 2. DTO에서 주문세부정보를 꺼내 주문세부테이블에 insert. 반복문필요
        for (OrderDetail data : orderRequestDTO.getOrderDetails()) {
            // 과제 4-7 주문입력 API에서 아래 예외를 추가하시오
            // 제품테이블의 재고보다 많은 양을 주문하는 경우 예외발생
            Product product = productRepository.getProductById(
                    data.getProductId());
            System.out.println(product.getStock() + " " + data.getOrderQuantity());
            if (product.getStock() - data.getOrderQuantity() < 0) {
                throw new InvalidRequestException(
                        "요청하신 수량은 현재 재고를 초과합니다: " +
                        product.getProductName() + ", 현재 재고 " +
                        product.getStock());
            }
            orderDetailRepository.saveOrderDetail(data);
        }
        return orderRequestDTO;
    }

    // 과제 4-4 주문번호와 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    public String updateOrderWithShippingDate(String id, String date) {
        orderRepository.updateOrderWithShippingDate(id, date);
        return "성공적으로 수정하였습니다";
    }

    // 과제 4-5 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    public List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit) {
        return orderRepository.getTopCitiesByTotalOrderAmount(limit);
    }

    // 과제 4-6 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    public List<Map<String, Double>> getOrderCountByYearForCity(String city) {
        return orderRepository.getOrderCountByYearForCity(city);
    }
}
