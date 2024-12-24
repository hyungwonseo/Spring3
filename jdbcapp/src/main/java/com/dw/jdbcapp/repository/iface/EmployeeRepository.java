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
    // 과제 4-3 입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
    // hiredate를 0으로 입력하면 가장 최근 입사한 사원의 정보를 조회하시오.
    List<Employee> getEmployeesByHiredate(String hiredate);
    List<Employee> getLastHiredEmployees();
}
