package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.OrderDetail;

import java.util.List;

public interface OrderDetailRepository {
    List<OrderDetail> getAllOrderDetails();
}
