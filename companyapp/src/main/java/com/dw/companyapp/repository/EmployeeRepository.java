package com.dw.companyapp.repository;

import com.dw.companyapp.dto.EmployeeDepartmentDTO;
import com.dw.companyapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("select e.hireDate, d.departmentName, e.name " +
            "from Employee e join e.department d")
    List<Object[]> getEmployeesWithDepartName();

    @Query("select new com.dw.companyapp.dto.EmployeeDepartmentDTO(e.hireDate, d.departmentName, e.name) " +
            "from Employee e left join e.department d") // 부서가 없는 직원까지 보려면 left join
    List<EmployeeDepartmentDTO> getEmployeesWithDepartName2();

    // 과제 1-3 부서번호와 직위를 기준으로 해당 부서에 근무하는 특정 직위의 사원 정보를 조회하는 API
    @Query("select e from Employee e " +
            "where e.department.departmentId = :departmentNumber " +
            "and e.position = :position")
    List<Employee> getEmployeesWithDepartmentAndPosition(
            String departmentNumber, String position);

    // 과제 4-3 입사일을 매개변수로 해당 입사일 이후로 입사한 사원들을 조회하는 API
    @Query("select e from Employee e where e.hireDate >= :hiredate")
    List<Employee> getEmployeesByHiredate(LocalDate hiredate);
    // hiredate를 0으로 입력하면 가장 최근 입사한 사원의 정보를 조회하시오.
    @Query("select e from Employee e where e.hireDate = " +
            "(select e.hireDate from Employee e order by e.hireDate " +
            "desc limit 1)")
    List<Employee> getLastHiredEmployees();
}
