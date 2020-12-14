package com.company.utils;

public class Parsers {
    public static int integer(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int passportNum(String str) {
        return str.length() == 7 ? integer(str) : 0;
    }

    public static float float_(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
