package com.checker.controller;

import com.checker.Exception.CheckException;
import com.checker.util.CheckUtil;
import com.checker.util.ClimbUtil;
import com.checker.util.ResultHandler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        List<String> sameList = CheckUtil.getSame(source,target,CheckUtil.getUnits(source));
        Double sameRate = CheckUtil.getSameRate(source,sameList);
        Double similarRate = CheckUtil.getSimilarRate(source,target);
        return ResultHandler.handleResult(sameList,sameRate,similarRate);
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
            List<String> sameList = CheckUtil.getSame(source,result,CheckUtil.getUnits(source));
            Double sameRate = CheckUtil.getSameRate(source,sameList);
            Double similarRate = CheckUtil.getSimilarRate(source,result);
            Map<String,Object> a_url = ResultHandler.getResult(sameList,sameRate,similarRate);
            a_url.put("url",webRoot + url);
            urlList.add(a_url);
        }
        return ResultHandler.handleResult(urlList);
    }
}
