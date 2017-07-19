package com.sena.service.impl;

import com.sena.model.FileInfo;
import com.sena.service.FileService;
import com.sena.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.InputStream;
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

            //得到输入流
            InputStream inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = FileUtil.readInputStream(inputStream);
            return new String(getData);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
