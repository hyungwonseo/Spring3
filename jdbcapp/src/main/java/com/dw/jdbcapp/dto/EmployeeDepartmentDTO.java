package com.dw.jdbcapp.dto;

import java.time.LocalDate;

public class EmployeeDepartmentDTO {
    private LocalDate hireDate;
    private String departmentName;
    private String employeeName;

    public EmployeeDepartmentDTO() {
    }

    public EmployeeDepartmentDTO(LocalDate hireDate, String departmentName, String employeeName) {
        this.hireDate = hireDate;
        this.departmentName = departmentName;
        this.employeeName = employeeName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return this.hireDate + ", " + this.departmentName + ", " +
                this.employeeName;
    }
}
