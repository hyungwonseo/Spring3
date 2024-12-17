package com.dw.jdbcapp.repository.iface;

import com.dw.jdbcapp.model.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> getAllDepartments();
    Department saveDepartment(Department department);
    Department updateDepartment(Department department);
    String deleteDepartment(String id);
}
