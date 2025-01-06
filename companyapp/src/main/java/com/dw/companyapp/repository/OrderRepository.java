package com.dw.companyapp.repository;

import com.dw.companyapp.dto.CityOrderAmountDTO;
import com.dw.companyapp.dto.YearOrderCountDTO;
import com.dw.companyapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("select o from Order o where o.customer.customerId = :customerId and o.orderId " +
            "in (select od.order.orderId from OrderDetail od where od.product.productId = :productNumber)")
    List<Order> getOrderByIdAndCustomer(int productNumber, String customerId);

    // 과제 4-5 도시별로 주문금액합 결과를 내림차순 정렬하여 조회하는 API
    @Query("select new com.dw.companyapp.dto.CityOrderAmountDTO(c.city, " +
            "sum(od.unitPrice * od.orderQuantity) as orderAmount) " +
            "from OrderDetail od join od.order.customer c " +
            "group by c.city order by orderAmount desc limit :limit")
    List<CityOrderAmountDTO> getTopCitiesByTotalOrderAmount(int limit);

    // 과제 4-6 도시를 매개변수로 해당 도시의 년도별 주문건수를 조회하는 API
    @Query("select new com.dw.companyapp.dto.YearOrderCountDTO(year(o.orderDate), count(o.orderId)) " +
            "from Order o join o.customer c " +
            "where c.city = :city group by year(o.orderDate)")
    List<YearOrderCountDTO> getOrderCountByYearForCity(String city);
}
