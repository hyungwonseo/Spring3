package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("find-all-orderdetails")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return new ResponseEntity<>(
                orderDetailService.getAllOrderDetails(),
                HttpStatus.OK);
    }
}
