package com.util;

import com.learninggenie.common.exception.LearningGenieRuntimeException;
import com.learninggenie.common.utils.tls.ImConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static String doGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlName = url + "?" + param;
            URL realUrl = new URL(urlName);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += "/n" + line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url,String encoding)  {
        String result = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建GET方式请求对象
        HttpGet get = new HttpGet(url);

        try {
            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(get);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                System.out.println("响应状态码:"+ response.getStatusLine());
                System.out.println("-------------------------------------------------");
                //按指定编码转换结果实体为String类型
                result = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        }catch (IOException e){
            throw new LearningGenieRuntimeException("send post failed");
        }
        logger.info(result);
        return result;
    }

    public static String sendPost(String url, String json, String encoding) {
        String result = "";
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        //设置参数到请求对象中
        httpPost.setEntity(new StringEntity(json,encoding));
        logger.info("请求地址：" + url);
        logger.info("请求参数：" + json);
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        try {
            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = client.execute(httpPost);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            logger.info("响应状态码:"+ response.getStatusLine());
            logger.info("-------------------------------------------------");
            if (entity != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
                //按指定编码转换结果实体为String类型
                result = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        }catch (IOException e){
            throw new LearningGenieRuntimeException("send post failed");
        }

        return result;
    }

    public static String formatUrl(String serviceName, String cmdName) {
        String identifier = ImConfig.IDENTIFIER;
        if (StringUtil.isEmptyOrBlank(ImConfig.sig))
            ImConfig.generateSig();
        long sdkAppId = ImConfig.QC_SDK_APP_ID;
        String url = String.format("%s/v4/%s/%s?usersig=%s&identifier=%s&sdkappid=%s&contenttype=json",
                ImConfig.QC_IM_HOST, serviceName, cmdName, ImConfig.sig, identifier, sdkAppId);
        return url;
    }
    public static void main(String[] args) throws Exception {
//        doGet("http://192.168.1.109:1002/api/v1/agencies/identifier","type=&search=a&order=asc");

//        String url="https://php.weather.sina.com.cn/iframe/index/w_cl.php";
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "js");
//        map.put("day", "0");
//        map.put("city", "上海");
//        map.put("dfc", "1");
//        map.put("charset", "utf-8");
//        String body = doPost(url, map,"utf-8");
//        System.out.println("交易响应结果：");
//        System.out.println(body);
//
//        System.out.println("-----------------------------------");
//
//        map.put("city", "北京");
//        body = doPost(url, map, "utf-8");
//        System.out.println("交易响应结果：");
//        System.out.println(body);

        sendGet("http://192.168.1.109:1002/api/v1/agencies/identifier?type=&search=a&order=asc","utf-8");

//        String url = "http://192.168.1.109:1002/api/v1/portfolios/statistics";
//        Map<String, Object> map = new HashMap<>();
//        map.put("groupIds", "0CA5BD74-4ED9-E611-A884-90FBA6071C06");
//        map.put("portfolioId", "831102DD-2AEE-E411-AF66-02C72B94B99B");
//        map.put("alias", "2016-2017 Summer");
//        map.put("allGroupIds", "0CA5BD74-4ED9-E611-A884-90FBA6071C06");
//        map.put("charset", "utf-8");
//        map.put("special", true);
//        String body = doPost(url, map,"utf-8");
//        System.out.println("交易响应结果：");
//        System.out.println(body);

//        String url = "http://192.168.1.109:1002/api/v1/agencies/identifier/E0718580-3B36-499D-9FA7-EF4954703052";
//        Map<String,Object> map = new HashMap<>();
//        map.put("ids","4F72C103-B057-E611-8620-90FBA6071C06,07A17B29-2D6B-E611-A906-90FBA6071C06,B8043A1E-4847-E611-8B08-E09467EB0A90");
//        doPost(url, map,"utf-8");

    }
}
