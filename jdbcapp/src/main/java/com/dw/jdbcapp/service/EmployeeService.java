package com.dw.jdbcapp.service;

import com.dw.jdbcapp.dto.EmployeeDepartmentDTO;
import com.dw.jdbcapp.exception.ResourceNotFoundException;
import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import com.dw.jdbcapp.repository.jdbc.EmployeejdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    @Qualifier("employeeTemplateRepository")
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.getEmployeeById(id);
    }

    public List<Map<String,Object>> getEmployeesWithDepartName() {
        return employeeRepository.getEmployeesWithDepartName();
    }

    public List<EmployeeDepartmentDTO> getEmployeesWithDepartName2() {
        List<EmployeeDepartmentDTO> employeeDepartmentDTOList =
                new ArrayList<>();

        List<Map<String,Object>> mapList =
                employeeRepository.getEmployeesWithDepartName();

        for(Map<String,Object> data : mapList) {
            EmployeeDepartmentDTO temp = new EmployeeDepartmentDTO(
                    LocalDate.parse((String)data.get("입사일")),
                    (String)data.get("부서명"),
                    (String)data.get("이름")
            );
            employeeDepartmentDTOList.add(temp);
        }
        return employeeDepartmentDTOList;
    }

    public List<Employee> getEmployeesWithDepartmentAndPosition(
            String departmentNumber, String position
    ) {
        // 과제 3-3 부서번호와 직위로 사원정보를 조회할때 데이터가 없는 경우의 예외처리
        List<Employee> employees = employeeRepository.getEmployeesWithDepartmentAndPosition(
                departmentNumber, position);
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException(
                    "해당되는 정보가 없습니다: " + departmentNumber + ", " +
                    position);
        }
        return employees;
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.saveEmployee(employee);
    }
}








