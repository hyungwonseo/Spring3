package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.Department;
import com.dw.jdbcapp.repository.iface.DepartmentRepository;
import com.dw.jdbcapp.repository.jdbc.DepartmentjdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    @Qualifier("departmentTemplateRepository")
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDepartments();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.saveDepartment(department);
    }

    public List<Department> saveDepartmentList(
                           List<Department> departmentList) {
        for (Department data : departmentList) {
            departmentRepository.saveDepartment(data);
        }
        return departmentList;
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.updateDepartment(department);
    }

    public String deleteDepartment(String id) {
        return departmentRepository.deleteDepartment(id);
    }
}








