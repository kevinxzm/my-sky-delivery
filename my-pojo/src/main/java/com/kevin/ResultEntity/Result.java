package com.kevin.ResultEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;   // 状态码：1 成功，0 失败
    private String msg; // 返回消息
    private T data;         // 返回数据（泛型）

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(1, msg, data);
    }


    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    public static Result<String> success(String msg) {
        return new Result<>(1, msg, null);
    }


    public static <T> Result<T> error(String msg) {
        return new Result<>(0, msg, null);
    }

}
