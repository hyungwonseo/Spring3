package com.dw.companyapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="고객")
public class Customer {
    @Id
    @Column(name="고객번호")
    private String customerId;
    @Column(name="고객회사명")
    private String companyName;
    @Column(name="담당자명")
    private String contactName;
    @Column(name="담당자직위")
    private String contactTitle;
    @Column(name="주소")
    private String address;
    @Column(name="도시")
    private String city;
    @Column(name="지역")
    private String region;
    @Column(name="전화번호")
    private String phoneNumber;
    @Column(name="마일리지")
    private int mileage;
}
