package com.checker.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jacqard {
    public static double JacqardDistance(String origin, String target){
        List<String> originUnits = CheckUtil.getUnits(origin);
        List<String> targetUnits = CheckUtil.getUnits(target);
        Map<String,Integer> originMap = new HashMap<>();
        double sim = 0;
        double sum = originUnits.size() + targetUnits.size();
        for(String unit: originUnits){
            if(originMap.containsKey(unit)){
                originMap.put(unit,originMap.get(unit)+1);
            }
            else {
                originMap.put(unit,1);
            }
        }
        for(String unit: targetUnits){
            if(originMap.containsKey(unit)){
                sim ++;
                originMap.put(unit,originMap.get(unit)-1);
                if(originMap.get(unit)==0) originMap.remove(unit);
            }
        }
        return CheckUtil.doubleFormat(sim/sum);
    }
}
