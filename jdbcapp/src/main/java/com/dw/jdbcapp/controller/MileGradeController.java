package com.dw.jdbcapp.controller;

import com.dw.jdbcapp.model.Customer;
import com.dw.jdbcapp.model.MileGrade;
import com.dw.jdbcapp.service.CustomerService;
import com.dw.jdbcapp.service.MileGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MileGradeController {
    @Autowired
    MileGradeService mileGradeService;

    @GetMapping("/find-all-mileage")
    public ResponseEntity<List<MileGrade>> getAllMileages() {
        return new ResponseEntity<>(
                mileGradeService.getAllMileages(),
                HttpStatus.OK);
    }
}
