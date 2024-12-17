package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Employee;
import com.dw.jdbcapp.repository.iface.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeTemplateRepository implements EmployeeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getAllEmployees() {
        return List.of();
    }

    @Override
    public Employee getEmployeeById(String id) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getEmployeesWithDepartName() {
        String query = "select 이름, 입사일, 부서명 from 사원 " +
                "inner join 부서 on 사원.부서번호 = 부서.부서번호";
        return jdbcTemplate.query(query, (rs, rowNum)->{
            Map<String, Object> employee = new HashMap<>();
            employee.put("이름", rs.getString("이름"));
            employee.put("입사일", rs.getString("입사일"));
            employee.put("부서명", rs.getString("부서명"));
            return employee;
        });
    }

    @Override
    public List<Employee> getEmployeesWithDepartmentAndPosition(String departmentNumber, String position) {
        return List.of();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return null;
    }
}
