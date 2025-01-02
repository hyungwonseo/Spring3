package com.dw.companyapp.repository;

import com.dw.companyapp.model.MileageGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageGradeRepository extends JpaRepository<MileageGrade, String> {
}
