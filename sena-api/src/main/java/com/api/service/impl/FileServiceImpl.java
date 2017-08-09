package com.api.service.impl;

import com.api.enums.SearchIpType;
import com.api.service.FileService;
import com.api.util.HttpUtil;
import com.api.util.JsonUtil;
import com.api.model.FileInfo;
import com.api.util.StringUtil;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sena on 2017-07-19.
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public FileInfo getFile(String fileId) {
        return null;
    }

    @Override
    public Object getIp(String ip, String type) {
        try {
            URL url = this.getUrl(ip, type);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(20 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setConnectTimeout(5 * 1000);
            //conn.setRequestMethod("utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            //得到输入流
            InputStream inputStream = conn.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            String str = buffer.toString();
            Object object = JsonUtil.fromJson(str, Object.class);
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private URL getUrl(String ip, String type) throws Exception {
        URL url;
        if (StringUtil.isEmptyOrBlank(type)) {
            String key = "TKUBZ-D24AF-GJ4JY-JDVM2-IBYKK-KEBCU";
            url = new URL("https://apis.map.qq.com/ws/location/v1/ip?ip=" + ip + "&key=" + key);
        } else if (SearchIpType.BAIDU.toString().equalsIgnoreCase(type)) {
            String ak = "UUv4OK3kHazq7HXgNdgojiD2Bq08lv8M"; // = "NHlwpVgqvW91kETp9zyvQqi0nEfesEcC";
            //url = new URL("http://api.map.baidu.com/highacciploc/v1?qcip=" + ip + "&qterm=pc&ak=" + ak + "&coord=bd09ll&extensions=3");
            url = new URL("http://api.map.baidu.com/location/ip?coor=bd09ll&ak=" + ak + "&ip=" + ip);
        } else if (SearchIpType.QQ.toString().equalsIgnoreCase(type)) {
            String key = "TKUBZ-D24AF-GJ4JY-JDVM2-IBYKK-KEBCU";
            url = new URL("https://apis.map.qq.com/ws/location/v1/ip?ip=" + ip + "&key=" + key);
        } else if (SearchIpType.IP_API.toString().equalsIgnoreCase(type)) {
            url = new URL("http://ip-api.com/json/?ip=" + ip);
        } else {
            String key = "TKUBZ-D24AF-GJ4JY-JDVM2-IBYKK-KEBCU";
            url = new URL("https://apis.map.qq.com/ws/location/v1/ip?ip=" + ip + "&key=" + key);
        }
        return url;
    }

    public String postTest() {
        String json = "";
        try {
            json = HttpUtil.sendPost("", JsonUtil.toJson(""), HTTP.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return json;
        }
    }
}
