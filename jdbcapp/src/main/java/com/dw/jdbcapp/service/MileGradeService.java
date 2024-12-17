package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.repository.iface.MileGradeRepository;
import com.dw.jdbcapp.repository.jdbc.MileGradejdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileGradeService {
    @Autowired
    @Qualifier("mileGradeTemplateRepository")
    MileGradeRepository mileGradeRepository;

    public List<MileGrade> getAllMileages() {
        return mileGradeRepository.getAllMileages();
    }
}
