package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.dto.OrderRequestDTO;
import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Order;
import com.dw.jdbcapp.service.CustomerService;
import com.dw.jdbcapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/find-all-orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(
                orderService.getAllOrders(),
                HttpStatus.OK);
    }

    // 과제 1-2 주문번호를 기준으로 주문 정보를 조회하는 API
    @GetMapping("/orders/{orderNumber}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderNumber) {
        return new ResponseEntity<>(
                orderService.getOrderById(orderNumber),
                HttpStatus.OK);
    }

    // 과제 1-4 제품번호와 고객번호를 기준으로 해당 제품을 주문한 특정 고객의 주문 내역을 조회하는 API
    @GetMapping("/orders/{productNumber}/{customerId}")
    public ResponseEntity<List<Order>> getOrderByIdAndCustomer(@PathVariable int productNumber,
                @PathVariable String customerId) {
        return new ResponseEntity<>(
                orderService.getOrderByIdAndCustomer(productNumber, customerId),
                HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderRequestDTO> saveOrder(
            @RequestBody OrderRequestDTO orderRequestDTO) {
        return new ResponseEntity<>(
                orderService.saveOrder(orderRequestDTO),
                HttpStatus.CREATED);
    }

    // 과제 4-4 주문번호와 발송일을 매개변수로 해당 주문의 발송일을 수정하는 API
    @PutMapping("/orders/update")
    public ResponseEntity<String> updateOrderWithShippingDate(
            @RequestParam String id, @RequestParam String date) {
        return new ResponseEntity<>(
                orderService.updateOrderWithShippingDate(id, date),
                HttpStatus.OK);
    }

    // 과제 4-5 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    @GetMapping("/orders/city/orderamount/{limit}")
    public ResponseEntity<List<Map<String, Double>>>
        getTopCitiesByTotalOrderAmount(@PathVariable int limit) {
        return new ResponseEntity<>(
                orderService.getTopCitiesByTotalOrderAmount(limit),
                HttpStatus.OK);
    }

    // 과제 4-6 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    @GetMapping("/orders/ordercount/year/{city}")
    public ResponseEntity<List<Map<String, Double>>>
        getOrderCountByYearForCity(@PathVariable String city) {
        return new ResponseEntity<>(
                orderService.getOrderCountByYearForCity(city),
                HttpStatus.OK);
    }
}
