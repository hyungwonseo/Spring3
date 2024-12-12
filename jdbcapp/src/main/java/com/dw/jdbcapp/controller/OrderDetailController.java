package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("find-all-orderdetails")
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }
}
