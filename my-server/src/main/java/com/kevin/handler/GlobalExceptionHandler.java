package com.kevin.handler;


import com.kevin.ResultEntity.Result;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler
//    public String doExceptionHandle(BaseE e) {
//        System.out.println("global exception: "+e.getMessage()+ "!");
//        return "global exception: "+e.getMessage();
//    }


    @ExceptionHandler
    public Result doSqlExceptionHandle(SQLIntegrityConstraintViolationException e) {
        System.out.println("SQLIntegrityConstraintViolationException: "+e.getMessage()+ "!");
        return Result.error(e.getMessage());
    }


    // 捕获删除数据时数据不存在的错误
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Result handleEmptyResult(EmptyResultDataAccessException e) {
        return Result.error("删除失败，数据不存在: " + e.getMessage());
    }




}
