package com.dw.companyapp.service;

import com.dw.companyapp.model.Customer;
import com.dw.companyapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // 과제 4-1 전체 평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    public List<Customer> getCustomersWithHighMileThanAvg() {
        return customerRepository.getCustomersWithHighMileThanAvg();
    }

    // 과제 4-2 마일리지등급을 매개변수로 해당 마일리지등급을 가진 고객들을 조회하는 API
    public List<Customer> getCustomersByMileageGrade(String grade) {
        return customerRepository.getCustomersByMileageGrade(grade);
    }
}
