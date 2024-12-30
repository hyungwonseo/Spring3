package com.dw.jpaapp.repository;

import com.dw.jpaapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByName(String name);
    Student findByEmail(String email);
    Student findByNameAndEmail(String name, String email);
    Student findByNameLike(String name);
}
