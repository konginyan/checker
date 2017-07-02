package com.checker.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClimbTest {
    @Test
    public void climb() throws Exception{
        String source = "今天的波斯湾已是烽火连天，对于我们，与历史上的战争不同的是，我们正" +
                "经历着一场人类历史上第一次的战争电视直播。" +
                "这其中，突出在我们的视野内的，是中央电视台与凤凰卫视." +
                "关文献的基础上，结合我国庭审直播的立法现状以及国际上关于庭审直播的规定，" +
                "分别从实体上、程序上、例外情况以及权利的救" +
                "济、立法形式等角度探讨如何用法律规范和调整庭审直播案件" +
                "［关键词］《直播中国》；现场直播；传播观念；社会责任 电" +
                "视技术的发展使电视新闻的采集、加工、传递变得更为快捷有效。在世界电视业日益激烈的新闻竞争中，" +
                "量身定做的奥运，与其说电视直播奥运，毋宁说电视在制造一个别样的“奥运”。" +
                "电视愈益成为一种现代“仪式” ，遵循和探求“真实”不再是它的重要职志。 ";
        Map<String,Map<String,Object>> map = new HashMap<>();
        String webRoot = "http://www.lunwencloud.com";
        Elements h3s = ClimbUtil.filter(ClimbUtil.climb(webRoot + "/plus/search.php?kwtype=0&searchtype=titlekeyword&q=直播&typeid=124"),ClimbUtil.CLS, "left");
        Elements as = ClimbUtil.filter(h3s.first(),ClimbUtil.ATTR,"target","_blank");
        List<Element> elements = as.stream().filter(x -> x.attr("href").contains("html")).collect(Collectors.toList());
        Elements urls = new Elements(elements);
        urls.forEach(System.out::println);

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
            System.out.println(webRoot + url + ResultHandler.getResult(sameList,sameRate,similarRate,1,10));
        }
    }
}
