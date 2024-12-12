package com.dw.jdbcapp.model;

import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String name;
    private String englishName;
    private String position;
    private String gender;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String address;
    private String city;
    private String region;
    private String homePhone;
    private String supervisorId;
    private String departmentId;

    public Employee() {
    }

    public Employee(String employeeId, String name, String englishName, String position, String gender, LocalDate birthDate, LocalDate hireDate, String address, String city, String region, String homePhone, String supervisorId, String departmentId) {
        this.employeeId = employeeId;
        this.name = name;
        this.englishName = englishName;
        this.position = position;
        this.gender = gender;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
        this.city = city;
        this.region = region;
        this.homePhone = homePhone;
        this.supervisorId = supervisorId;
        this.departmentId = departmentId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return employeeId + ", " + name + ", " + englishName + ", " + position + ", " + gender + ", " + birthDate + ", " + hireDate + ", " + address + ", " + city + ", " + region + ", " + homePhone + ", " + supervisorId + ", " + departmentId;
    }
}
