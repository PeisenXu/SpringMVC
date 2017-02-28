package com.util;

import com.learninggenie.common.exception.LearningGenieRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonProperties {

    private static String getProperty(String key){
        try{
            Properties props=new Properties();
            InputStream is= ResourceUtil.getResourceAsStream("common.properties", CommonProperties.class.getClassLoader());
            props.load(is);
            return props.getProperty(key);
        }catch (IOException e){
            throw new LearningGenieRuntimeException("read common.properties file failed.",e);
        }
    }
}
