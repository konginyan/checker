package com.checker.util;

public class EncodeUtil {
    public static String ConvertDecimalNCRToString(String hex)
    {
        String myString = hex.replace("&#", "").replaceAll("\u0093\u0094\u0091","");
        String[] split = myString.split(";");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < split.length; i++)
        {
            sb.append((char)Integer.parseInt(split[i]));
        }
        return sb.toString();
    }
}
