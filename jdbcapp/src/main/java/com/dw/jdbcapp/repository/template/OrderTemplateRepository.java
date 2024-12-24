package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.repository.iface.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // 과제 3-2 주문정보를 조회할때 주문번호가 올바르지 않은 경우의 예외 처리
        try{
            return jdbcTemplate.queryForObject(query, orderRowMapper, orderNumber);
        }catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(
                    "존재하지 않는 주문번호입니다" + orderNumber);
        }
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

    @Override
    public int saveOrder(Order order) {
        String query = "insert into 주문(주문번호,고객번호,사원번호,주문일,요청일) " +
                "values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query,
                order.getOrderId(),
                order.getCustomerId(),
                order.getEmployeeId(),
                order.getOrderDate().toString(),
                order.getRequestDate().toString());
    }

    // 과제 4-4 주문번호와 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    @Override
    public int updateOrderWithShippingDate(String id, String date) {
        String query = "update 주문 set 발송일 = ? where 주문번호 = ?";
        return jdbcTemplate.update(query, date, id);
    }

    // 과제 4-5 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    @Override
    public List<Map<String, Double>> getTopCitiesByTotalOrderAmount(int limit) {
        String query = "select 도시, sum(단가 * 주문수량) as 주문금액합 " +
                "from 주문 join 고객 on 주문.고객번호 = 고객.고객번호 " +
                "join 주문세부 on 주문.주문번호 = 주문세부.주문번호 " +
                "group by 도시 order by 주문금액합 desc limit ?";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Map<String, Double> map = new HashMap<>();
            map.put(rs.getString("도시"),
                    rs.getDouble("주문금액합"));
            return map;
        }, limit);
    }

    // 과제 4-6 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    @Override
    public List<Map<String, Double>> getOrderCountByYearForCity(String city) {
        String query = "select year(주문일) as 년도, count(*) as 주문건수 " +
                "from 주문 join 고객 on 주문.고객번호 = 고객.고객번호\n" +
                "join 주문세부 on 주문.주문번호 = 주문세부.주문번호\n" +
                "where 도시 = ? group by 년도";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Map<String, Double> map = new HashMap<>();
            map.put(rs.getString("년도"),
                    rs.getDouble("주문건수"));
            return map;
        }, city);
    }
}
