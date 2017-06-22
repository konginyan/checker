package com.checker.util;

public class fuzzyMatch {
    public static int cal(String s1, String s2){
        int min = 0;
        int[][] table = new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++) table[i][0]=i;
        for(int i=0;i<=s2.length();i++) table[0][i]=i;
        for(int i=1;i<=s1.length();i++) {
            for (int j = 1; j <=s2.length(); j++) {
                min = (table[i][j-1]+1)>(table[i-1][j]+1)?table[i-1][j]+1:table[i][j-1]+1;
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    min = min>table[i-1][j-1]?table[i-1][j-1]:min;
                }
                else {
                    min = min>(table[i-1][j-1]+1)?(table[i-1][j-1]+1):min;
                }
                table[i][j] = min;
            }
        }
        return table[s1.length()-1][s2.length()-1];
    }

    public static double getMatchRate(int count, int size){
        return CheckUtil.doubleFormat(1-(double)count/size);
    }
}
