package com.dw.companyapp.service;

import com.dw.companyapp.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    public List<Customer> getAllCustomers() {
        return null;
    }

    // 과제 4-1 전체 평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    public List<Customer> getCustomersWithHighMileThanAvg() {
        return null;
    }

    // 과제 4-2 마일리지등급을 매개변수로 해당 마일리지등급을 가진 고객들을 조회하는 API
    public List<Customer> getCustomersByMileageGrade(String grade) {
        return null;
    }
}
