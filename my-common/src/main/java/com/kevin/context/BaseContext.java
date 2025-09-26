package com.kevin.context;

public class BaseContext {
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setTokenId(Long tokenId) {
        threadLocal.set(tokenId);
    }

    public static Long getTokenId() {
        return threadLocal.get();
    }

    public static void removeTokenId() {
        threadLocal.remove();
    }

    public static void setName(Long tokenId) {
        threadLocal.set(tokenId);
    }

    public static Long getName() {
        return threadLocal.get();
    }
}
