package com.dw.companyapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="주문")
public class Order {
    @Id
    @Column(name="주문번호")
    private String orderId;
    @ManyToOne
    @JoinColumn(name="고객번호")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="사원번호")
    private Employee employee;
    @Column(name="주문일")
    private LocalDate orderDate;
    @Column(name="요청일")
    private LocalDate requestDate;
    @Column(name="발송일")
    private LocalDate shippingDate;
}
