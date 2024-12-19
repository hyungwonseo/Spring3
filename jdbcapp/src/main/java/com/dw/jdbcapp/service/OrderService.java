package com.dw.jdbcapp.service;

import com.dw.jdbcapp.dto.OrderRequestDTO;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.repository.iface.OrderDetailRepository;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import com.dw.jdbcapp.repository.jdbc.OrderJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    @Qualifier("orderTemplateRepository")
    OrderRepository orderRepository;
    @Autowired
    @Qualifier("orderDetailTemplateRepository")
    OrderDetailRepository orderDetailRepository;

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

    public OrderRequestDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        orderRepository.saveOrder(orderRequestDTO.toOrder());
        for (OrderDetail data : orderRequestDTO.getOrderDetails()) {
            orderDetailRepository.saveOrderDetail(data);
        }
        return orderRequestDTO;
    }
}
