package com.dw.jdbcapp.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 이 클래스의 역할은 개발자가 만든 사용자정의예외들을 모두 직접 응답처리하는 것
// 응답메시지의 형태와 내용을 모두 결정할 수 있어서 편리함
// 백엔드와 프론트엔드 개발자 사이에 협의된 형태로 전달하는 것이 일반적임!!
@Order(Ordered.HIGHEST_PRECEDENCE) // 중복된 예외처리의 우선순위를 결정
@RestControllerAdvice 
// 컨트롤러,서비스,레포지토리의 예외발생을 모두 이 클래스가 담당하도록 하는 것
public class CustomExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    // ()안에 선언한 예외클래스를 핸들링하는 메서드라는 의미
    // 자바에서는 클래스이름만 필요한 경우에는 반드시 클래스명.class를 사용해야함
    public ResponseEntity<String> handleInvalidRequestException(
            InvalidRequestException e) {
        return new ResponseEntity<>(
                e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(
            ResourceNotFoundException e) {
        return new ResponseEntity<>(
                e.getMessage(), HttpStatus.NOT_FOUND);
    }
}








