package com.luczin.bankapi.infra;

public class Utils {
    public static boolean isNull(Object... objects){
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] == null || objects[i] == "" || objects[i] == " "){
                return true;
            }
        }
        return false;
    }
}
