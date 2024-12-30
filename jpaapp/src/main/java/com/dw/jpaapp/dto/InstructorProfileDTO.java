package com.dw.jpaapp.dto;

import com.dw.jpaapp.model.Instructor;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InstructorProfileDTO {
    private Long id;
    private String bio;
    private String githubUrl;
    private Long instructorId;
}
