package com.company.utils;

import java.util.regex.Pattern;

public class Validator {
    private static final String timeRegex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public static boolean validateTime(String str) {
        return Pattern.compile(timeRegex).matcher(str.trim()).matches();
    }
}
