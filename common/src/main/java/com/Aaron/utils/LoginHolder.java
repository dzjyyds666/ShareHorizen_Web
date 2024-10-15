package com.Aaron.utils;


import org.springframework.stereotype.Component;

@Component
public class LoginHolder {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String userId)
    {
        threadLocal.set(userId);
    }

    public static String get()
    {
        return threadLocal.get();
    }

    public static void remove()
    {
        threadLocal.remove();
    }
}
