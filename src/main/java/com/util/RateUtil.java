package com.util;

import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by haoran on 2016/11/2.
 */
public class RateUtil {
    private static final String FRAMEWORK_PS_ID = "A5845474-BDCE-E411-AF66-02C72B94B99B";
    private static final String FRAMEWORK_IT_ID = "E163164F-BDCE-E411-AF66-02C72B94B99B";
    private static final String FRAMEWORK_K_ID = "A21BC800-2FF5-E411-AF66-02C72B94B99B";
    private static final String FRAMEWORK_SA_ID = "832D146E-EB57-E511-B553-0608F0CADB57";

    private static final Map<String, Set<String>> FRAMEWORKS_MAP = new HashMap<>();
    static {
        // PS模版及子模版
        FRAMEWORKS_MAP.put(FRAMEWORK_PS_ID, new HashSet<String>() {{
            add("230F7A3C-4F05-48C2-BA95-E2137A327C6E");
            add("D961B237-D92E-4468-915B-0158ECF503AB");
            add("44DD7D49-F203-4AB9-B432-75DEC7D6E60D");
            add("965C3E49-2283-40DC-94A3-90DCAC565FB4");
            add("5C7089F8-B2C9-419C-914D-6B1B88F6FCB0");
            add("4372805E-DDF6-4F3B-99A6-32D69B46E055");
            add("864B887F-50C4-4DEB-AB9B-61AFFAF011BB");
            add("8C93F947-EB96-4AFC-8A71-F46F83DADA0A");
            add("824E4590-5565-40C1-91D5-3F0BF3AF14F4");
            add("49DA6264-E437-E611-AB42-06BC895D03FD");
            add("965C3E49-2283-40DC-94A3-90DCAC565FB4");
            add("8C93F947-EB96-4AFC-8A71-F46F83DADA0A");
            add("4372805E-DDF6-4F3B-99A6-32D69B46E055");
        }});
        FRAMEWORKS_MAP.put(FRAMEWORK_IT_ID, new HashSet<String>(){{}});
        FRAMEWORKS_MAP.put(FRAMEWORK_K_ID, new HashSet<String>(){{}});
        FRAMEWORKS_MAP.put(FRAMEWORK_SA_ID, new HashSet<String>(){{}});
    }

    /**
     * 判断评分模版是不是PS
     * @param frameworkId
     * @return
     */
    public static boolean isPSFramework(String frameworkId) {
        if (frameworkId == null)
            return false;
        frameworkId = frameworkId.toUpperCase();
        // 如果是PS模版，直接返回true
        if (FRAMEWORK_PS_ID.equalsIgnoreCase(frameworkId))
            return true;
        // 再判断是否是PS子模版
        if (FRAMEWORKS_MAP.containsKey(FRAMEWORK_PS_ID)) {
            Set<String> subFrameworks = FRAMEWORKS_MAP.get(FRAMEWORK_PS_ID);
            if (subFrameworks.contains(frameworkId))
                return true;
        }
        return false;
    }

    public static boolean isITFramework(String frameworkId) {
        if (frameworkId == null)
            return false;
        frameworkId = frameworkId.toUpperCase();
        // 如果是PS模版，直接返回true
        if (FRAMEWORK_IT_ID.equalsIgnoreCase(frameworkId))
            return true;
        // 再判断是否是PS子模版
        if (FRAMEWORKS_MAP.containsKey(FRAMEWORK_IT_ID)) {
            Set<String> subFrameworks = FRAMEWORKS_MAP.get(FRAMEWORK_IT_ID);
            if (subFrameworks.contains(frameworkId))
                return true;
        }
        return false;
    }

    public static boolean isKFramework(String frameworkId) {
        if (frameworkId == null)
            return false;
        frameworkId = frameworkId.toUpperCase();
        // 如果是PS模版，直接返回true
        if (FRAMEWORK_K_ID.equalsIgnoreCase(frameworkId))
            return true;
        // 再判断是否是PS子模版
        if (FRAMEWORKS_MAP.containsKey(FRAMEWORK_K_ID)) {
            Set<String> subFrameworks = FRAMEWORKS_MAP.get(FRAMEWORK_K_ID);
            if (subFrameworks.contains(frameworkId))
                return true;
        }
        return false;
    }

    public static boolean isSAFramework(String frameworkId) {
        if (frameworkId == null)
            return false;
        frameworkId = frameworkId.toUpperCase();
        // 如果是PS模版，直接返回true
        if (FRAMEWORK_SA_ID.equalsIgnoreCase(frameworkId))
            return true;
        // 再判断是否是PS子模版
        if (FRAMEWORKS_MAP.containsKey(FRAMEWORK_SA_ID)) {
            Set<String> subFrameworks = FRAMEWORKS_MAP.get(FRAMEWORK_SA_ID);
            if (subFrameworks.contains(frameworkId))
                return true;
        }
        return false;
    }
}
