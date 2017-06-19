package com.checker.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Method;

public class ClimbUtil {
    public static final String TAG = "getElementsByTag";
    public static final String CLS = "getElementsByClass";
    public static final String ID = "getElementById";
    public static final String ATTR = "getElementsByAttributeValue";

    public static Document climb(String url) throws IOException{
        return Jsoup.connect(url)
                .timeout(10000)           // 设置连接超时时间
                .get();
    }

    public static Elements filter(Element root, String filter, String tag) throws Exception{
        Method method = Element.class.getMethod(filter,String.class);
        Elements elements = (Elements) method.invoke(root, tag);
        return elements;
    }

    public static Elements filter(Element root, String filter, String tag, String value) throws Exception{
        Method method = Element.class.getMethod(filter,String.class,String.class);
        Elements elements = (Elements) method.invoke(root, tag, value);
        return elements;
    }
}
