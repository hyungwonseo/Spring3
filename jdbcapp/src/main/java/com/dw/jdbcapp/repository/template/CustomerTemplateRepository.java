package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.repository.iface.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerTemplateRepository implements CustomerRepository {
    // JDBC Template은 반드시 JdbcTemplate 객체를 의존성 주입받아 사용해야 함
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Customer> customerRowMapper
            = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getString("고객번호"));
            customer.setCompanyName(rs.getString("고객회사명"));
            customer.setContactName(rs.getString("담당자명"));
            customer.setContactTitle(rs.getString("담당자직위"));
            customer.setAddress(rs.getString("주소"));
            customer.setCity(rs.getString("도시"));
            customer.setRegion(rs.getString("지역"));
            customer.setPhone(rs.getString("전화번호"));
            customer.setMileage(rs.getInt("마일리지"));
            return customer;
        }
    };

    @Override
    public List<Customer> getAllCustomers() {
        String query = "select * from 고객";
        return jdbcTemplate.query(query, customerRowMapper);
    }

    // 과제 4-1 전체 평균마일리지보다 큰 마일리지를 가진 고객들을 조회하는 API
    @Override
    public List<Customer> getCustomersWithHighMileThanAvg() {
        String query = "select * from 고객 where 마일리지 > " +
                "(select avg(마일리지) from 고객)";
        return jdbcTemplate.query(query, customerRowMapper);
    }

    // 과제 4-2 마일리지등급을 매개변수로 해당 마일리지등급을 가진 고객들을 조회하는 API
    @Override
    public List<Customer> getCustomersByMileageGrade(String grade) {
        String query = "select 고객.* from 고객 join 마일리지등급 " +
                "on 고객.마일리지 between 마일리지등급.하한마일리지 and 마일리지등급.상한마일리지\n" +
                "where 마일리지등급.등급명 = ?";
        return jdbcTemplate.query(query, customerRowMapper, grade);
    }
}
