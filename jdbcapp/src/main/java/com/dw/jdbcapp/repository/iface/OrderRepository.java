package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrders();
    Order getOrderById(String orderNumber);
    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);
    int saveOrder(Order order);
}
