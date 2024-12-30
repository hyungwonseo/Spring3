package com.dw.jpaapp.model;

import com.dw.jpaapp.dto.CourseDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor_fk;

    @ManyToMany
    @JoinTable(name = "course_student",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentList = new ArrayList<>();

    // CourseDTO 매핑 메서드
    public CourseDTO toDTO() {
        List<Long> studentIds = studentList.stream()
                .map(Student::getId).toList();
        return new CourseDTO(this.id, this.title, this.description,
                this.instructor_fk.getId(), studentIds);
    }
}






