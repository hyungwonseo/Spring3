package com.dw.companyapp.service;

import com.dw.companyapp.dto.CityOrderAmountDTO;
import com.dw.companyapp.dto.OrderRequestDTO;
import com.dw.companyapp.dto.YearOrderCountDTO;
import com.dw.companyapp.exception.InvalidRequestException;
import com.dw.companyapp.exception.ResourceNotFoundException;
import com.dw.companyapp.model.Order;
import com.dw.companyapp.model.OrderDetail;
import com.dw.companyapp.model.Product;
import com.dw.companyapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // 과제 1-2 주문번호를 기준으로 주문 정보를 조회하는 API
    // 과제 3-2 주문정보를 조회할때 주문번호가 올바르지 않은 경우의 예외 처리
    public Order getOrderById(String orderNumber) {
        return orderRepository.findById(orderNumber)
                .orElseThrow(()->new InvalidRequestException("없는 주문번호"));
    }

    // 과제 1-4 제품번호와 고객번호를 기준으로 해당 제품을 주문한 특정 고객의 주문 내역을 조회하는 API
    // 과제 3-4 제품번호와 고객번호로 주문정보를 조회할때 데이터가 없는 경우의 예외처리
    public List<Order> getOrderByIdAndCustomer(int productNumber, String customerId) {
        List<Order> orders = orderRepository.getOrderByIdAndCustomer(productNumber, customerId);
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("데이터 없음");
        }
        return orders;
    }

    public OrderRequestDTO saveOrder(OrderRequestDTO orderRequestDTO) {
        Order order = new Order();
        order.setOrderId(orderRequestDTO.getOrderId());
        order.setCustomer(customerRepository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(()->new InvalidRequestException("없는 고객번호")));
        order.setEmployee(employeeRepository.findById(orderRequestDTO.getEmployeeId())
                .orElseThrow(()->new InvalidRequestException("없는 사원번호")));
        order.setOrderDate(LocalDate.now());
        order.setRequestDate(orderRequestDTO.getRequestDate());
        orderRepository.save(order);

        for (OrderDetail data : orderRequestDTO.getOrderDetails()) {
            Product product = productRepository.findById(data.getProduct().getProductId())
                    .orElseThrow(()->new InvalidRequestException("없는 제품번호"));
            if (product.getStock() - data.getOrderQuantity() < 0) {
                throw new InvalidRequestException(
                        "요청하신 수량은 현재 재고를 초과합니다: " +
                                product.getProductName() + ", 현재 재고 " +
                                product.getStock());
            }
            orderDetailRepository.save(data);
        }
        return orderRequestDTO;
    }

    // 과제 4-4 주문번호와 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    public String updateOrderWithShippingDate(String id, String date) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new InvalidRequestException("없는 주문번호"));
        order.setShippingDate(LocalDate.parse(date));
        orderRepository.save(order);
        return "성공적으로 수정하였습니다";
    }

    // 과제 4-5 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    public List<CityOrderAmountDTO> getTopCitiesByTotalOrderAmount(int limit) {
        return orderRepository.getTopCitiesByTotalOrderAmount(limit);
    }

    // 과제 4-6 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    public List<YearOrderCountDTO> getOrderCountByYearForCity(String city) {
        return orderRepository.getOrderCountByYearForCity(city);
    }
}

//{
//    "orderId": "Q1111",
//    "customerId": "ACDDR",
//    "employeeId": "E01",
//    "requestDate": "2025-02-01",
//    "orderDetails": [
//        {
//            "order": {
//                "orderId": "Q1111"
//            },
//            "product": {
//                "productId": 1
//            },
//            "unitPrice": 8000,
//            "orderQuantity": 10,
//            "discountRate": 0
//        }
//    ]
//}
