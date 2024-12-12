package com.dw.jdbcapp.model;

public class OrderDetail {
    private String orderId;
    private int productId;
    private double unitPrice;
    private int orderQuantity;
    private double discountRate;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, int productId, double unitPrice, int orderQuantity, double discountRate) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.orderQuantity = orderQuantity;
        this.discountRate = discountRate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return orderId + ", " + productId + ", " + unitPrice + ", " + orderQuantity + ", " + discountRate;
    }
}
