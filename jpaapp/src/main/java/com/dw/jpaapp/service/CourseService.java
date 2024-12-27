package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.CourseDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(Course::toDTO)
                .collect(Collectors.toList());
    }
}
