package com.dw.jdbcapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStatus의 역할은 사용자정의예외의 기본 http상태코드를 결정하는것임
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException() {
        super();
    }
    public InvalidRequestException(String message) {
        super(message);
    }
}
