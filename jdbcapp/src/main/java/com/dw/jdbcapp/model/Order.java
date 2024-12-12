package com.dw.jdbcapp.model;

import java.time.LocalDate;

public class Order {
    private String orderId;
    private String customerId;
    private String employeeId;
    private LocalDate orderDate;
    private LocalDate requestDate;
    private LocalDate shippingDate;

    public Order() {
    }

    public Order(String orderId, String customerId, String employeeId, LocalDate orderDate, LocalDate requestDate, LocalDate shippingDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requestDate = requestDate;
        this.shippingDate = shippingDate;
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

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    @Override
    public String toString() {
        return orderId + ", " + customerId + ", " + employeeId + ", " + orderDate + ", " + requestDate + ", " + shippingDate;
    }
}
