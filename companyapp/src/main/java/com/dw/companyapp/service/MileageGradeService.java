package com.dw.companyapp.service;

import com.dw.companyapp.model.MileageGrade;
import com.dw.companyapp.repository.MileageGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MileageGradeService {
    @Autowired
    MileageGradeRepository mileageGradeRepository;

    public List<MileageGrade> getAllMileages() {
        return mileageGradeRepository.findAll();
    }
}
