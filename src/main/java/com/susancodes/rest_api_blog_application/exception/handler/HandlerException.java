package com.susancodes.rest_api_blog_application.exception.handler;

import com.susancodes.rest_api_blog_application.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> response(ResourceNotFoundException e){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value()).
                build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }


}
