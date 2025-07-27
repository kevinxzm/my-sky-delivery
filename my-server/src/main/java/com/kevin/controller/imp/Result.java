package com.kevin.controller.imp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {
    private int code;
    private Object data;
    private String msg;
}
