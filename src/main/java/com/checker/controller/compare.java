package com.checker.controller;

import com.checker.Exception.CheckException;
import com.checker.util.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class compare {
    @GetMapping(value = "/")
    public String hello(){
        return "hello checker system!";
    }

    @PostMapping(value = "/check")
    public Map<String,Object> check(@RequestParam("src") String source,
                                      @RequestParam("tar") String target) throws CheckException{
//        List<String> sameList = CheckUtil.getSame(source,target,CheckUtil.getUnits(source));
        List<String> sameListFull = fullMatch.cal(source,target);
        Double sameRate = CheckUtil.getSameRate(source,sameListFull);
        int needStep  = fuzzyMatch.cal(source, target);
//        int size = source.length()>target.length()?source.length():target.length();
//        Double matchRate = fuzzyMatch.getMatchRate(needStep,size);
        double jacDis = Jacqard.JacqardDistance(source,target);
        Double similarRate = CheckUtil.getSimilarRate(source,target);
        return ResultHandler.handleResult(sameListFull,sameRate,jacDis,needStep,source.length());
    }

    @PostMapping(value = "/auto_check")
    public Map<String,Object> autoCheck(@RequestParam("src") String source,
                                        @RequestParam("key") String title) throws Exception{
        List<Object> urlList = new ArrayList<Object>();
        String webRoot = "http://www.lunwencloud.com";
        Elements h3s = ClimbUtil.filter(ClimbUtil.climb(webRoot + "/plus/search.php?kwtype=0&searchtype=titlekeyword&q="+title+"&typeid=124"),ClimbUtil.CLS, "left");
        Elements as = ClimbUtil.filter(h3s.first(),ClimbUtil.ATTR,"target","_blank");
        List<Element> elements = as.stream().filter(x -> x.attr("href").contains("html")).collect(Collectors.toList());
        Elements urls = new Elements(elements);

        for(Element e:urls){
            String result = "";
            String url = e.attr("href");
            Elements page = ClimbUtil.filter(ClimbUtil.climb(webRoot + url),ClimbUtil.CLS,"news_bd");
            for(Element p:page){
                result += p.text();
            }
//            List<String> sameList = CheckUtil.getSame(source,result,CheckUtil.getUnits(source));
            List<String> sameListFull = fullMatch.cal(source,result);
            Double sameRate = CheckUtil.getSameRate(source,sameListFull);
            int needStep  = fuzzyMatch.cal(source, result);
//            int size = source.length()>result.length()?source.length():result.length();
//            Double matchRate = fuzzyMatch.getMatchRate(needStep,size);
            Double similarRate = CheckUtil.getSimilarRate(source,result);
            Map<String,Object> a_url = ResultHandler.getResult(sameListFull,sameRate,similarRate,needStep,source.length());
            a_url.put("url",webRoot + url);
            urlList.add(a_url);
        }
        return ResultHandler.handleResult(urlList);
    }
}
