package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.CourseDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    StudentRepository studentRepository;

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream().map(Course::toDTO)
                .collect(Collectors.toList());
    }

    // 과제5-1. 검색어를 매개변수로 전달하고 검색어를 포함한 title을 가진 과목을 조회
    public List<CourseDTO> getCoursesLike(String title) {
        return courseRepository.findByTitleLike("%" + title + "%")
                .stream().map(Course::toDTO).toList();
    }

    // 과제5-2. 과목 정보를 새로 저장
    public CourseDTO saveCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setInstructor_fk(instructorRepository.findById(courseDTO.getInstructorId())
                .orElseThrow(()->new RuntimeException("No instructor")));
        course.setStudentList(courseDTO.getStudentIds().stream()
                .map(id->studentRepository.findById(id))
                .map(optional->optional.orElseThrow(()->new RuntimeException("No Student")))
                .toList()
        );
        return courseRepository.save(course).toDTO();
    }
}
