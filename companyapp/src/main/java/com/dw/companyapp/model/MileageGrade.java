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
@Table(name="마일리지등급")
public class MileageGrade {
    @Id
    @Column(name="등급명")
    private String gradeName;
    @Column(name="하한마일리지")
    private int lowerMileage;
    @Column(name="상한마일리지")
    private int upperMileage;
}
