package com.util;

import com.learninggenie.common.exception.LearningGenieRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ResourceUtil {

    private ResourceUtil() {
    }

    public static String getResourceAsString(String file) {
        return getResourceAsString(file, Thread.currentThread().getContextClassLoader());
    }

    public static String getResourceAsString(String file, Class caller) {
        return getResourceAsString(file, caller.getClassLoader());
    }

    private static String getResourceAsString(String file, ClassLoader loader) {
        try (InputStream iStream = getResourceAsStream(file, loader)){
            String result = (new Scanner(iStream)).useDelimiter("\\A").next();
            return result;
        } catch (IOException var5) {
            throw new LearningGenieRuntimeException("Error closing input stream.", var5);
        }
    }

    public static InputStream getResourceAsStream(String file) {
        return getResourceAsStream(file,Thread.currentThread().getContextClassLoader());
    }

    public static InputStream getResourceAsStream(String file,Class caller) {
        return getResourceAsStream(file,caller.getClassLoader());
    }

    public static InputStream getResourceAsStream(String file, ClassLoader loader) {
        InputStream iStream = loader.getResourceAsStream(file);
        if(iStream == null) {
            throw new LearningGenieRuntimeException("Could not locate the file \'" + file + "\'.");
        } else {
            return iStream;
        }
    }

    public static String createHtmlFile(Object data, String templateName) {
        String jsonData = JsonUtil.toJson(data);
        String template = ResourceUtil.getResourceAsString(templateName);
        String htmlStr = template.replace("@{data}", jsonData);

        String htmlFilePath = FileUtil.randomTempFilePath(".html");
        FileUtil.createStringFile(htmlFilePath, htmlStr);
        return htmlFilePath;
    }
}
