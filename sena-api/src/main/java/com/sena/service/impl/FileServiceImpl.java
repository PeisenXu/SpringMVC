package com.sena.service.impl;

import com.sena.model.FileInfo;
import com.sena.service.FileService;
import com.sena.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
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
    public String getIp(String ip) {
        try {
            String ak = "NHlwpVgqvW91kETp9zyvQqi0nEfesEcC";
            URL url = new URL("http://api.map.baidu.com/highacciploc/v1?qcip=" + ip + "&qterm=pc&ak=" + ak + "&coord=bd09ll&extensions=3");
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
            while ((line = in.readLine()) != null){
                buffer.append(line);
            }
            String str = buffer.toString();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
