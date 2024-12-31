package com.dw.jpaapp.controller;

import com.dw.jpaapp.dto.CourseDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return new ResponseEntity<>(
                courseService.getAllCourses(),
                HttpStatus.OK);
    }

    // 과제5-1. 검색어를 매개변수로 전달하고 검색어를 포함한 title을 가진 과목을 조회
    @GetMapping("/course/search")
    public ResponseEntity<List<CourseDTO>> getCoursesLike(
            @RequestParam String title) {
        return new ResponseEntity<>(
                courseService.getCoursesLike(title),
                HttpStatus.OK);
    }

    // 과제5-2. 과목 정보를 새로 저장
    @PostMapping("/course/save")
    public ResponseEntity<CourseDTO> saveCourse(
            @RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(
                courseService.saveCourse(courseDTO),
                HttpStatus.CREATED);
    }
}
