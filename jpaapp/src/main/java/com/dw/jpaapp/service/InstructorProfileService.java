package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.InstructorProfileDTO;
import com.dw.jpaapp.model.InstructorProfile;
import com.dw.jpaapp.repository.InstructorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorProfileService {
    @Autowired
    InstructorProfileRepository instructorProfileRepository;

    public List<InstructorProfileDTO> getAllInstructorProfiles() {
        return instructorProfileRepository.findAll().stream()
                .map(InstructorProfile::toDTO).toList();
    }
}
