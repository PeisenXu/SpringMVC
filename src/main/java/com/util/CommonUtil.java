package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haoran on 2016/10/10.
 */
public class CommonUtil {
    private static final Map<String, String> domainColorMap = new HashMap<String, String>(){{
        put("ATL-REG", "#0082c8");
        put("SED", "#c60170");
        put("SSD", "#c60170");
        put("LLD", "#fdbb2e");
        put("ELD", "#01529a");
        put("SPAN", "#01529a");
        put("COG", "#00a94d");
        put("COG:MATH", "#94D0C3");
        put("MATH", "#94D0C3");
        put("COG:SCI", "#2FAA4F");
        put("PD-HLTH", "#f47d21");
        put("PD", "#f47d21");
        put("HLTH", "#B05436");
        put("HSS", "#ff0000");
        put("VPA", "#65035f");
    }};

    public static String getDomainIcon(String domain) {
        return "http://d2urtjxi3o4r5s.cloudfront.net/images/" + domain + "_ICON.png";
    }

    public static String getDomainColor(String domain) {
        if (domain == null)
            return "";
        domain = domain.replace("–","-").toUpperCase();
        if (domainColorMap.containsKey(domain))
            return domainColorMap.get(domain);
        return "";
    }
}
