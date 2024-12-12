package com.dw.jdbcapp.repository;

import com.dw.jdbcapp.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        return customers;
    }
}
