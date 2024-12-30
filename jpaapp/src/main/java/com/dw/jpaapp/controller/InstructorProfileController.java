package com.dw.jpaapp.controller;

import com.dw.jpaapp.dto.InstructorProfileDTO;
import com.dw.jpaapp.service.InstructorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorProfileController {
    @Autowired
    InstructorProfileService instructorProfileService;

    @GetMapping("/profiles")
    public ResponseEntity<List<InstructorProfileDTO>> getAllInstructorProfiles() {
        return new ResponseEntity<>(
                instructorProfileService.getAllInstructorProfiles(),
                HttpStatus.OK);
    }
}
