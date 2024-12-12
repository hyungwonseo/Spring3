package com.dw.jdbcapp.model;

public class Product {
    private int productId;
    private String productName;
    private String packageUnit;
    private double unitPrice;
    private int stock;

    public Product() {
    }

    public Product(int productId, String productName, String packageUnit, double unitPrice, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.packageUnit = packageUnit;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return productId + ", " + productName + ", " + packageUnit + ", " + unitPrice + ", " + stock;
    }
}
