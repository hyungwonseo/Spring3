package com.dw.companyapp.controller;

import com.dw.companyapp.dto.EmployeeDepartmentDTO;
import com.dw.companyapp.model.Employee;
import com.dw.companyapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/find-all-employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(
                employeeService.getAllEmployees(),
                HttpStatus.OK);
    }

    // 과제 3-1 사원정보를 조회할때 사원번호가 올바르지 않은 경우의 예외 처리
    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam String id) {
        return new ResponseEntity<>(
                employeeService.getEmployeeById(id),
                HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById_2(@PathVariable String id) {
        return new ResponseEntity<>(
                employeeService.getEmployeeById(id),
                HttpStatus.OK);
    }

    @GetMapping("/employees/department")
    public ResponseEntity<List<Map<String,Object>>> getEmployeesWithDepartName() {
        return new ResponseEntity<>(
                employeeService.getEmployeesWithDepartName(),
                HttpStatus.OK);
    }

    @GetMapping("/employees/department2")
    public ResponseEntity<List<EmployeeDepartmentDTO>> getEmployeesWithDepartName2() {
        return new ResponseEntity<>(
                employeeService.getEmployeesWithDepartName2(),
                HttpStatus.OK);
    }

    // 과제 1-3 부서번호와 직위를 기준으로 해당 부서에 근무하는 특정 직위의 사원 정보를 조회하는 API
    // 과제 3-3 부서번호와 직위로 사원정보를 조회할때 데이터가 없는 경우의 예외처리
    @GetMapping("/employees/{departmentNumber}/{position}")
    public ResponseEntity<List<Employee>> getEmployeesWithDepartmentAndPosition(
            @PathVariable String departmentNumber,
            @PathVariable String position
    ) {
        return new ResponseEntity<>(
                employeeService.getEmployeesWithDepartmentAndPosition(
                departmentNumber, position),
                HttpStatus.OK);
    }

    // 과제 2-3 사원테이블에 사원 1명을 새로 추가하는 API
    @PostMapping("/post/employee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(
                employeeService.saveEmployee(employee),
                HttpStatus.CREATED);
    }

    // 과제 4-3 입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
    // hiredate를 0으로 입력하면 가장 최근 입사한 사원의 정보를 조회하시오.
    @GetMapping("/employees/hiredate/{hiredate}")
    public ResponseEntity<List<Employee>>  getEmployeesByHiredate(
            @PathVariable String hiredate) {
        return new ResponseEntity<>(
                employeeService.getEmployeesByHiredate(hiredate),
                HttpStatus.OK);
    }
}







