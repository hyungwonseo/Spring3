package com.dw.jdbcapp.model;

public class Customer {
    private String customerId; // 고객번호
    private String companyName; // 고객회사명
    private String contactName; // 담당자명
    private String contactTitle; // 담당자직위
    private String address; // 주소
    private String city; // 도시
    private String region; // 지역
    private String phone; // 전화번호
    private int mileage; // 마일리지

    // 기본 생성자
    public Customer() {
    }

    // 매개변수 생성자
    public Customer(String customerId, String companyName, String contactName, String contactTitle,
                    String address, String city, String region, String phone, int mileage) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.phone = phone;
        this.mileage = mileage;
    }

    // Getter 및 Setter 메서드
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", companyName='" + companyName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactTitle='" + contactTitle + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", phone='" + phone + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}

