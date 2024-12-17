package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(String id);
    List<Map<String,Object>> getEmployeesWithDepartName();
    List<Employee> getEmployeesWithDepartmentAndPosition(
            String departmentNumber, String position
    );
    Employee saveEmployee(Employee employee);
}
