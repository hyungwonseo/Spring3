package com.dw.companyapp.dto;

import com.dw.companyapp.model.OrderDetail;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class OrderRequestDTO {
    private String orderId;
    private String customerId;
    private String employeeId;
    private LocalDate requestDate;
    private List<OrderDetail> orderDetails;
}
