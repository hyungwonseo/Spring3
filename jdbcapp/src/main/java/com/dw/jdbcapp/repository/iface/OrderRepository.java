package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    List<Order> getAllOrders();
    Order getOrderById(String orderNumber);
    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);
    int saveOrder(Order order);
    // 과제 4-4 주문번호와 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    int updateOrderWithShippingDate(String id, String date);
    // 과제 4-5 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    List<Map<String,Double>> getTopCitiesByTotalOrderAmount(int limit);
    // 과제 4-6 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    List<Map<String,Double>> getOrderCountByYearForCity(String city);
}
