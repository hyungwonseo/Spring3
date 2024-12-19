package com.dw.jdbcapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    // 사용자정의: SQL 쿼리의 결과에 오류가 있을 경우 발생하는 예외
    // 조건에 맞는 데이터를 못찾거나 데이터베이스를 확인해봐야 알 수 있는
    // 요청상의 오류도 함께 포함함!!
    public ResourceNotFoundException() {
        super();
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
