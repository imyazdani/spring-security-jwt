package com.example.springsecurityjwt.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException(
            UserNotFoundException ex, HttpServletRequest request) {

        var body = createBody(ex, request);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<Object> userDuplicateException(
            UserDuplicateException ex, HttpServletRequest request) {

        var body = createBody(ex, request);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    private Map<String, Object> createBody(Exception ex, HttpServletRequest request){
        log.error(ex.getClass().getSimpleName(), ex);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", ex.getClass().getSimpleName());
        body.put("message", ex.getMessage());
        body.put("path", request.getServletPath());

        return body;
    }
}
