package com.example.springbootend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class handleValidException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handleValidException(MethodArgumentNotValidException exception){
        StringBuilder builder = new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach( e ->{
            builder.append(e.getField());
            builder.append(":");
            builder.append(e.getDefaultMessage());
            builder.append(";");
        });
        return Map.of("message",builder.toString());
    }
}
