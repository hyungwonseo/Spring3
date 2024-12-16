package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.dto.EmployeeDepartmentDTO;
import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.service.CustomerService;
import com.dw.jdbcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find-all-employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Query Parameters (쿼리 문자열)
    @GetMapping("/employee")
    public Employee getEmployeeById(@RequestParam String id) {
        return employeeService.getEmployeeById(id);
    }
    // Path Parameters (경로 매개변수)
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById_2(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees/department")
    public List<Map<String,Object>> getEmployeesWithDepartName() {
        return employeeService.getEmployeesWithDepartName();
    }
    @GetMapping("/employees/department2")
    public List<EmployeeDepartmentDTO> getEmployeesWithDepartName2() {
        return employeeService.getEmployeesWithDepartName2();
    }
}







