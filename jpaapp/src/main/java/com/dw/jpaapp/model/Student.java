package com.dw.jpaapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student") // 명시적으로 테이블이름 설정
public class Student {
    @Id // PK로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이디숫자 자동증가
    @Column(name = "id") // @Id를 사용하면 안써도 됨
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "studentList")
    private List<Course> courseList = new ArrayList<>();
}
