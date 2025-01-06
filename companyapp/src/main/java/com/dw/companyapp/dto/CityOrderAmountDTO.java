package com.dw.companyapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CityOrderAmountDTO {
    private String city;
    private Double orderAmount;
}
