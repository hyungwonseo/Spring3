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
@Table(name="제품")
public class Product {
    @Id
    @Column(name="제품번호")
    private long productId;
    @Column(name="제품명")
    private String productName;
    @Column(name="포장단위")
    private String pkgUnit;
    @Column(name="단가")
    private double unitPrice;
    @Column(name="재고")
    private int stock;
}
