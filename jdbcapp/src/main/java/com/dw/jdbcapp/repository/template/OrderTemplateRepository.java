package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderTemplateRepository implements OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Order> orderRowMapper = (rs, rowNum) -> {
        Order order = new Order();
        order.setOrderId(rs.getString("주문번호"));
        order.setCustomerId(rs.getString("고객번호"));
        order.setEmployeeId(rs.getString("사원번호"));
        order.setOrderDate(rs.getObject("주문일", LocalDate.class));
        order.setRequestDate(rs.getObject("요청일", LocalDate.class));
        order.setShippingDate(rs.getObject("발송일", LocalDate.class));
        return order;
    };

    @Override
    public List<Order> getAllOrders() {
        String query = "select * from 주문";
        return jdbcTemplate.query(query, orderRowMapper);
    }

    @Override
    public Order getOrderById(String orderNumber) {
        String query = "select * from 주문 where 주문번호 = ?";
        return jdbcTemplate.queryForObject(query, orderRowMapper, orderNumber);
    }

    @Override
    public List<Order> getOrderByIdAndCustomer(int productNumber,
                                               String customerId) {
        String query = "select * from 주문 where 고객번호 = ? and 주문번호 " +
                "in (select 주문번호 from 주문세부 where 제품번호 = ?)";
        return jdbcTemplate.query(query,
                orderRowMapper,
                customerId,
                productNumber);
    }
}
