package com.dw.jpaapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InstructorGithubDTO {
    private Long id;
    private String name;
    private String githubUrl;
}
