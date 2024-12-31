package com.dw.jpaapp.repository;

import com.dw.jpaapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // 과제5-1. 검색어를 매개변수로 전달하고 검색어를 포함한 title을 가진 과목을 조회
    List<Course> findByTitleLike(String title);
}
