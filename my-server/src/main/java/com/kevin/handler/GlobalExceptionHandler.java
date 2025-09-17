package com.kevin.handler;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public String doExceptionHandle(BaseE e) {
//
//        System.out.println("global exception: "+e.getMessage()+ "!");
//
//        return "global exception: "+e.getMessage();
//    }


    @ExceptionHandler
    public String doSqlExceptionHandle(SQLIntegrityConstraintViolationException e) {

        System.out.println("SQLIntegrityConstraintViolationException: "+e.getMessage()+ "!");

        return "sql global exception: "+e.getMessage();
    }




}
