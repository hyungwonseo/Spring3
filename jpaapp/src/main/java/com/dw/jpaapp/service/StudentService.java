package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.StudentDTO;
import com.dw.jpaapp.model.Student;
import com.dw.jpaapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(Student::toDTO)
                .toList();
    }

    public String getStudentInfo() {
        return studentRepository.findByName("Steve").toString();
    }
}









