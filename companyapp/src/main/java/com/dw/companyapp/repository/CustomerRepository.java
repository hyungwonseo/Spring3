package com.dw.companyapp.repository;

import com.dw.companyapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    // 과제 4-1 전체 평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    @Query("select c from Customer c where c.mileage > " +
            "(select avg(c.mileage) from Customer c)")
    List<Customer> getCustomersWithHighMileThanAvg();

    // 과제 4-2 마일리지등급을 매개변수로 해당 마일리지등급을 가진 고객들을 조회하는 API
    @Query("select c from Customer c join MileageGrade m " +
            "on c.mileage between m.lowerMileage and m.upperMileage " +
            "where m.gradeName = :grade")
    public List<Customer> getCustomersByMileageGrade(String grade);
}
