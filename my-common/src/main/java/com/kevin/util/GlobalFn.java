package com.kevin.util;

public class GlobalFn {

    public static Long extractId(String token) {
        if (token == null || !token.contains(":")) {
            return null; // 或者抛异常，看你业务需求
        }
        String[] parts = token.split(":");
        try {
            return Long.parseLong(parts[1]);
        } catch (NumberFormatException e) {
            return null; // 说明不是合法数字
        }
    }
}
