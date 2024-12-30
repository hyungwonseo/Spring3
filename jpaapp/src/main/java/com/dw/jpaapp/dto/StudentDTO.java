package com.dw.jpaapp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<Long> courseIds = new ArrayList<>();
}
