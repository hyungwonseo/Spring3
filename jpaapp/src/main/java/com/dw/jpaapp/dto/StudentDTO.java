package com.dw.jpaapp.dto;

import com.dw.jpaapp.model.Course;
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
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<Long> courseIds = new ArrayList<>();
}
