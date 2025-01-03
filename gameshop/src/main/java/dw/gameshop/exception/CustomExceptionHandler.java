package dw.gameshop.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<Map<String, String>> handleInvalidRequestException(InvalidRequestException ex) {
        Map<String, String> errors = Map.of("Invalid Request",
                (ex.getMessage() != null ? ex.getMessage() : "No Exception Message"));
        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errors = Map.of("Resource Not Found",
                (ex.getMessage() != null ? ex.getMessage() : "No Exception Message"));
        return new ResponseEntity<>(
                errors,
                HttpStatus.NOT_FOUND);
    }
}