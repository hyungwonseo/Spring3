package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.OrderDetail;
import com.dw.jdbcapp.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.getAllOrderDetails();
    }
}
