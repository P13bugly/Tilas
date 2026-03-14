package com.example.tilas;

public class ThreadLocalTest {
    private static  final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Main Message");

        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

        threadLocal.remove();

        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
    }
}
