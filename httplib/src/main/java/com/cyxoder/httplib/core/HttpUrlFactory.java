package com.cyxoder.httplib.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Jade on 2017/4/25.
 */

public class HttpUrlFactory {
    /**
     * 生成单个URL
     *
     * @param baseUrl
     * @param mapping
     * @param params
     * @return
     */
    public static String createUrl(String baseUrl, String mapping, Map<String, String> params) {
        String url = baseUrl + mapping;
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
        if (params.isEmpty()) {
            return url;
        } else {
            url = url + "?";
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                url = url + key + "=" + value + "&";
            }
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

    /**
     * 生成多个URL
     *
     * @param baseUrls domains
     * @param mapping
     * @param params
     * @return
     */
    public static List<String> createUrls(List<String> baseUrls, String mapping, Map<String, String> params) {
        List<String> urls = new ArrayList<>();
        for (String base : baseUrls) {
            urls.add(createUrl(base, mapping, params));
        }
        return urls;
    }
}
