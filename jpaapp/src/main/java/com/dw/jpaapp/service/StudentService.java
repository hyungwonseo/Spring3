package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.StudentDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(Student::toDTO)
                .toList();
    }

    // JPA 메서드 쿼리를 연습하기 위한 예제
    // 여러 방식의 메서드쿼리를 수행해보는 연습 메서드
    public String getStudentInfo() {
        // findByName: 이름필드(name) 기준으로 학생 데이터를 조회하는 메서드쿼리
        //return studentRepository.findByName("Steve").toDTO().toString();
        // findByName이 List<Student>를 리턴하는 경우
//        return studentRepository.findByName("Steve").stream()
//                .map(Student::toDTO).toList().toString();
        // findByName이 Optional<Student>를 리턴하는 경우
        // Optional은 내부에 null 데이터를 안전하게 가질 수 있음
//        Optional<Student> student = studentRepository.findByName("Steve");
//        if (student.isPresent()) {
//            throw new RuntimeException("없는 데이터");
//        }
//        return student.get().toDTO().toString();

        // 위 코드의 람다식
        return studentRepository.findByName2("Steve")
                .map(Student::toDTO)
                .map(StudentDTO::toString)
                .orElseThrow(()->new RuntimeException("없는 데이터"));
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        List<Course> courseList = new ArrayList<>();
        for (Long id : studentDTO.getCourseIds()) {
            Optional<Course> courseOptional = courseRepository.findById(id);
            if (courseOptional.isPresent()) {
                Course course = courseOptional.get();
                course.getStudentList().add(student);
                courseList.add(course);
            }else {
                throw new RuntimeException("Course not found");
            }
        }
        /* 함수형 프로그래밍 #1 */
//        for (Long id : studentDTO.getCourseIds()) {
//            courseList.add(courseRepository.findById(id)
//                    .map(course-> {
//                        course.getStudentList().add(student);
//                        return course;
//                    })
//                    .orElseThrow(()->new RuntimeException("No Course")));
//        }
        /* 함수형 프로그래밍 #2 */
//        List<Course> courseList = studentDTO.getCourseIds().stream()
//                .map(id->courseRepository.findById(id))
//                .filter(Optional::isPresent)
//                .map(optional -> optional.orElseThrow(() -> new RuntimeException("Course not found")))
//                .peek(c-> c.getStudentList().add(student)) // peek은 스트림으로 받은 자료형을 그대로 유지하면서 코드를 수행하므로 리턴이 필요없음
//                .toList();
        student.setCourseList(courseList);
        return studentRepository.save(student).toDTO();
    }
}









