package com.dw.companyapp.controller;

import com.dw.companyapp.model.Department;
import com.dw.companyapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/find-all-departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(
                departmentService.getAllDepartments(),
                HttpStatus.OK);
    }

    @PostMapping("/post/department")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(
                departmentService.saveDepartment(department),
                HttpStatus.CREATED);
    }

    @PostMapping("/post/departmentlist")
    public ResponseEntity<List<Department>> saveDepartmentList(
            @RequestBody List<Department> departmentList) {
        return new ResponseEntity<>(
                departmentService.saveDepartmentList(departmentList),
                HttpStatus.CREATED);
    }

    @PutMapping("/put/department")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(
                departmentService.updateDepartment(department),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/department/id/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String id) {
        return new ResponseEntity<>(
                "부서번호 : " + departmentService.deleteDepartment(id)
                + " 가 삭제되었습니다.",
                HttpStatus.OK);
    }
}






