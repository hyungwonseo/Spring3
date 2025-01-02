package com.dw.companyapp.service;

import com.dw.companyapp.model.Department;
import com.dw.companyapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> saveDepartmentList(
                           List<Department> departmentList) {
        return departmentList.stream()
                .map(department -> departmentRepository.save(department))
                .toList();
    }

    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public String deleteDepartment(String id) {
        departmentRepository.deleteById(id);
        return id;
    }
}








