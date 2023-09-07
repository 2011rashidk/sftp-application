package com.happiest.minds.sftpapplication.exception;

import com.happiest.minds.sftpapplication.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> globalExceptionHandler(Exception ex) {
        log.error("Exception: {}", ex.getMessage());
        Response response = new Response(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Response> dataNotFoundException(DataNotFoundException ex) {
        log.error(ex.getMessage());
        Response response = new Response(HttpStatus.NOT_FOUND, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> accessDeniedException(AccessDeniedException e) {
        log.error(e.getMessage());
        Response response = new Response(HttpStatus.FORBIDDEN, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> badRequestException(BadRequestException e) {
        log.error(e.getMessage());
        Response response = new Response(HttpStatus.BAD_REQUEST, e.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
