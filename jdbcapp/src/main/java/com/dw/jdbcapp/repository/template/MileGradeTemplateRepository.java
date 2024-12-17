package com.dw.jdbcapp.repository.template;

import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.repository.iface.MileGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MileGradeTemplateRepository implements MileGradeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<MileGrade> mileGradeRowMapper = (rs, rowNum) -> {
        MileGrade mileGrade = new MileGrade();
        mileGrade.setGrade(rs.getString("등급명"));
        mileGrade.setLowerMileage(rs.getInt("하한마일리지"));
        mileGrade.setUpperMileage(rs.getInt("상한마일리지"));
        return mileGrade;
    };

    @Override
    public List<MileGrade> getAllMileages() {
        String query = "select * from 마일리지등급";
        return jdbcTemplate.query(query, mileGradeRowMapper);
    }
}
