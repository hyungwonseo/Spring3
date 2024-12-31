package com.dw.jpaapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentSummaryDTO {
    private Long 학생ID;
    private String 학생이름;
    private String 강의명;
    private String 강사이름;
}
