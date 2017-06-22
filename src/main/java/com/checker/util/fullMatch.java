package com.checker.util;

import java.util.ArrayList;
import java.util.List;

public class fullMatch {
    private static final int min = 10;
    public static List<String> cal(String s1, String s2) {
        List<String> result = new ArrayList<>();
        String temp = "";
        int len = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (CheckUtil.unitMatch(temp + s1.charAt(i), s2)) {
                temp += s1.charAt(i);
            } else {
                if(temp.matches("[A-Za-z\\s\\pP]+")){
                    len = temp.split("\\s|\\pP").length;
                }
                else len = temp.length();
                if(len>=min)result.add(temp);
                temp = "" + s1.charAt(i);
            }
        }

        if(temp.matches("[A-Za-z\\s\\pP]+")){
            len = temp.split("\\s|\\pP").length;
        }
        if(len>=min)result.add(temp);
        return result;
    }
}
