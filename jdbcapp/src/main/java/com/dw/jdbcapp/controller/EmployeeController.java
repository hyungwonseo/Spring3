package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.dto.EmployeeDepartmentDTO;
import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.service.CustomerService;
import com.dw.jdbcapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
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

    // 과제 1-3 부서번호와 직위를 기준으로 해당 부서에 근무하는 특정 직위의 사원 정보를 조회하는 API
    @GetMapping("/employees/{departmentNumber}/{position}")
    public List<Employee> getEmployeesWithDepartmentAndPosition(
            @PathVariable String departmentNumber,
            @PathVariable String position
    ) {
        return employeeService.getEmployeesWithDepartmentAndPosition(
                departmentNumber, position);
    }

    // 과제 2-3 사원테이블에 사원 1명을 새로 추가하는 API
    @PostMapping("/post/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}







