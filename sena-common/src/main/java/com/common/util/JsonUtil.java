package com.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
public class JsonUtil {
    private static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT){
        return gson.fromJson(json, classOfT);
    }

    public static <T> List<T> fromJsonArray(String json) {
        Type type = new TypeToken<List<T>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public static String toJson(Object object){
        return gson.toJson(object);
    }
}
