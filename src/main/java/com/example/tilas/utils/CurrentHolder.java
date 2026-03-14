package com.example.tilas.utils;

public class CurrentHolder {
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static void setCurrentLocal(Integer employeeId) {CURRENT_LOCAL.set(employeeId);}

    public static Integer getCurrentLocal() {
        return CURRENT_LOCAL.get();
    }

    public static void removeCurrentLocal(){CURRENT_LOCAL.remove();}
}
