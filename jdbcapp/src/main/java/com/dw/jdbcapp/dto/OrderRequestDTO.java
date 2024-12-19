package com.dw.jdbcapp.dto;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.model.OrderDetail;

import java.time.LocalDate;
import java.util.List;

public class OrderRequestDTO {
    private String orderId;
    private String customerId;
    private String employeeId;
    private LocalDate requestDate;
    private List<OrderDetail> orderDetails;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(String orderId, String customerId, String employeeId, LocalDate requestDate, List<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.requestDate = requestDate;
        this.orderDetails = orderDetails;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order toOrder() {
        Order order = new Order();
        order.setOrderId(this.orderId);
        order.setCustomerId(this.customerId);
        order.setEmployeeId(this.employeeId);
        order.setOrderDate(LocalDate.now());
        order.setRequestDate(this.requestDate);
        return order;
    }
}
