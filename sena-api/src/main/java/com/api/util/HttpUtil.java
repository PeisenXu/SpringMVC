package com.api.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static String sig;
    public static String identifier;

    public static String sendPost(String url, String json, String encoding) throws IOException {
        String result = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置参数到请求对象中
        httpPost.setEntity(new StringEntity(json, encoding));
        logger.info("请求地址：" + url);
        logger.info("请求参数：" + json);
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        logger.info("响应状态码:" + response.getStatusLine());
        logger.info("-------------------------------------------------");
        if (entity != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            //按指定编码转换结果实体为String类型
            result = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();

        return result;
    }

    public static String parseIp(HttpServletRequest headers){
        String ip;
        ip = headers.getHeader("X-Forwarded-For");
        if (StringUtil.isEmptyOrBlank(ip))
            ip = headers.getHeader("Proxy-Client-IP");
        if (StringUtil.isEmptyOrBlank(ip))
            ip = headers.getHeader("WL-Proxy-Client-IP");
        if (StringUtil.isEmptyOrBlank(ip))
            ip = headers.getHeader("HTTP_CLIENT_IP");
        if (StringUtil.isEmptyOrBlank(ip))
            ip = headers.getHeader("HTTP_X_FORWARDED_FOR");
        if (StringUtil.isEmptyOrBlank(ip))
            ip = headers.getRemoteAddr();
        return ip;
    }

}
