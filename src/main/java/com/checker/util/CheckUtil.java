package com.checker.util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.xq.algorithm.CosineSimilarAlgorithm.cosSimilarityByString;

public class CheckUtil {
    public static final String FORMAT = "#.00";

    public static double doubleFormat(double origin){
        DecimalFormat format = new DecimalFormat(FORMAT);
        return Double.parseDouble(format.format(origin));
    }

    public static List<String> getUnits(String origin) {
        List<String> units = new ArrayList<>();
        Reader input = new StringReader(origin);
        IKSegmenter iks = new IKSegmenter(input, true);
        Lexeme lexeme = null;
        try {
            while ((lexeme = iks.next()) != null) {
                units.add(lexeme.getLexemeText());
//                System.out.println(lexeme.getLexemeText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return units;
    }

    public static boolean unitMatch(String unit, String target){
        String reg = unit.replaceAll("\\\\","\\\\\\\\")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)")
                .replaceAll("\\[","\\\\[")
                .replaceAll("\\]","\\\\]")
                .replaceAll("\\?","\\\\?")
                .replaceAll("\\+","\\\\+")
                .replaceAll("\\*","\\\\*")
                .replaceAll("\\.","\\\\.");
        return target.matches(".*"+reg+".*");
    }

    public static String preTreat(String origin){
        return origin.trim().replaceAll("\\pP|\\s"," ");
    }

    public static String clearP(String origin){
        return origin.trim().replaceAll("\\pP|\\s","");
    }

    public static String clearWrap(String origin){
        return origin.replaceAll("\\r"," ").replaceAll("\\n"," ");
    }

    public static List<String> getSame(String origin, String target, List<String> units){
        origin = clearWrap(origin);
        target = clearWrap(origin);
        List<String> sameUnits = new ArrayList<>();
        List<String> engUnits = new ArrayList<>();
        int index = 0;
        int unitCount = 0;
        origin = preTreat(origin);
//        System.out.println(origin);
        String temp = "";
        for(String unit: units){
            while(origin.charAt(index)==' '){
                index++;
                unitCount=0;
                temp = "";
            }
            unitCount++;
            if(unit.matches("[a-zA-Z]+")) {
                engUnits.add(unit);
            }
            temp += unit;
            index += unit.length();
            if(unitCount==1)continue;
            if(unitMatch(temp,target)){
                if(unitCount>2) sameUnits.set(sameUnits.size()-1,temp);
                else sameUnits.add(temp);
            }
        }
        temp = "";
        int engCount = 0;
        for (String s: engUnits){
            temp += s + " ";
            engCount ++;
//            System.out.println(temp);
            if(engCount==1)continue;
            if(unitMatch(temp.trim(),target.toLowerCase())){
//                System.out.println(temp);
                if(engCount>2) sameUnits.set(sameUnits.size()-1,temp.trim());
                else sameUnits.add(temp.trim());
            }
            else {
                temp = s + " ";
                engCount = 1;
            }
        }
        return sameUnits;
    }

    public static List<String> getSame(String origin, String target){
        List<String> units = getUnits(origin);
        return getSame(origin,target,units);
    }

    public static Double getSameRate(String origin, List<String> sameUnits){
        double sameLength = sameUnits.stream().reduce("",String::concat).length();
        return doubleFormat(sameLength/origin.length());
    }

    public static Double getSameRate(String origin, String target){
        List<String> sameUnits = getSame(origin,target);
        return getSameRate(origin,sameUnits);
    }

    public static Double getSimilarRate(String origin, String target){
        return doubleFormat(cosSimilarityByString(origin,target));
    }
}
