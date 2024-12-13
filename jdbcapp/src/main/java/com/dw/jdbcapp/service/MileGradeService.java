package com.dw.jdbcapp.service;

import com.dw.jdbcapp.model.Department;
import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.repository.DepartmentRepository;
import com.dw.jdbcapp.repository.MileGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileGradeService {
    @Autowired
    MileGradeRepository mileGradeRepository;

    public List<MileGrade> getAllMileages() {
        return mileGradeRepository.getAllMileages();
    }
}
