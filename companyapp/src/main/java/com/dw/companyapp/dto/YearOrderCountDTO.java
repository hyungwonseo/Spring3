package com.dw.companyapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class YearOrderCountDTO {
    private Integer year;
    private Long orderCount; // JPQL에서 count()함수의 리턴형은 반드시 Long
}
