package com.dw.firstapp.repository;

import org.springframework.stereotype.Repository;

@Repository
public class HelloRepository {
    public String hello() {
        return "Hello world from repository";
    }
}
