package com.dw.companyapp.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="주문세부")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="주문세부번호")
    private long orderDetailId;
    @ManyToOne
    @JoinColumn(name="주문번호")
    private Order order;
    @ManyToOne
    @JoinColumn(name="제품번호")
    private Product product;
    @Column(name="단가")
    private double unitPrice;
    @Column(name="주문수량")
    private int orderQuantity;
    @Column(name="할인율")
    private double discountRate;
}
