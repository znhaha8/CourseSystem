package main.com.WCZZ.util;

import java.util.regex.Pattern;

public class IntergerCatcher {

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static void main(String[] args) {
        String str = "201610098309";
        System.out.println(isInteger(str));
    }
}
