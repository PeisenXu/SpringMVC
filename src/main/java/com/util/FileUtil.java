package com.util;

import com.learninggenie.common.exception.LearningGenieRuntimeException;
import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

/**
 * Created by LiWanXing on 2016/8/1.
 */
public class FileUtil {
    private static Logger log = LoggerFactory.getLogger(FileUtil.class);

    public static List<String[]> parseCsvFile(InputStreamReader reader) throws IOException {
        CSVReader csvReader = new CSVReader(reader);
        return csvReader.readAll();
    }

    /**
     * 创建文件目录
     * @param dir
     */
    public static File makeDir(String dir) {
        File dirFile = new File(dir);
        dirFile.mkdirs();
        dirFile.getParentFile().setReadable(true, false);
        dirFile.getParentFile().setWritable(true, false);
        dirFile.getParentFile().setExecutable(true, false);
        dirFile.setReadable(true, false);
        dirFile.setWritable(true, false);
        dirFile.setExecutable(true, false);
        return dirFile;
    }

    /**
     * 删除文件目录
     */
    public static void deleteDir(String dir) {
        try {
            File tempDir = new File(dir);
            if (tempDir.exists()) {
                FileUtils.deleteDirectory(new File(dir));
            }
        } catch (IOException e) {
            log.error("Delete dir failed." + dir, e);
        }
    }

    /**
     * 创建文件
     * @param path
     * @param data
     */
    public static void createStringFile(String path, String data) {
        try {
            File file = new File(path);
            FileUtils.writeStringToFile(file, data, "utf-8");
            file.setReadable(true, false);
            file.setWritable(true, false);
            file.setExecutable(true, false);
        } catch (IOException e) {
            throw new LearningGenieRuntimeException("Create file failed.", e);
        }
    }

    /**
     * 在临时目录下随机一个文件名
     * @param ext
     * @return
     */
    public static String randomTempFilePath(String ext) {
        String tempDir = System.getProperty("java.io.tmpdir");
        String path = tempDir + File.separator + UUID.randomUUID();
        if (ext != null)
            path += ext;
        return path;
    }

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static FileInputStream  downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(20*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
            saveDir.setReadable(true);
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }

        return new FileInputStream(file);
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
