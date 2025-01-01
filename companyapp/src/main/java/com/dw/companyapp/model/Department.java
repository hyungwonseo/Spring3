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
@Table(name="부서")
public class Department {
    @Id
    @Column(name="부서번호")
    private String departmentId;
    @Column(name="부서명")
    private String departmentName;
}
