package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Department;
import com.dw.jdbcapp.repository.iface.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartmentTemplateRepository implements DepartmentRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Department> departmentRowMapper = new RowMapper<Department>() {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(rs.getString("부서번호"));
            department.setDepartmentName(rs.getString("부서명"));
            return department;
        }
    };

    @Override
    public List<Department> getAllDepartments() {
        String query = "select * from 부서";
        return jdbcTemplate.query(query, departmentRowMapper);
    }

    @Override
    public Department saveDepartment(Department department) {
        String query = "insert into 부서(부서번호,부서명) "
                + "values (?, ?)";
        jdbcTemplate.update(query,
                department.getDepartmentId(),
                department.getDepartmentName());
        return department;
    }

    @Override
    public Department updateDepartment(Department department) {
        String query = "update 부서 set 부서명=? where 부서번호=?";
        jdbcTemplate.update(query,
                department.getDepartmentName(),
                department.getDepartmentId());
        return department;
    }

    @Override
    public String deleteDepartment(String id) {
        String query = "delete from 부서 where 부서번호 = ?";
        jdbcTemplate.update(query, id);
        return id;
    }
}
