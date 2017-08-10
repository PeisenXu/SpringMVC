package com.common.util;

import java.io.*;

/**
 * Created by Sena on 2016/10/18.
 */
// http://www.javaworld.com/article/2071275/core-java/when-runtime-exec---won-t.html?page=2
public class StreamGobbler extends Thread {
    private InputStream is;
    private String type;
    private OutputStream os;

    public StreamGobbler(InputStream is, String type) {
        this(is, type, null);
    }

    public StreamGobbler(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = null;
            if (os != null)
                pw = new PrintWriter(os);

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (pw != null)
                    pw.println(line);
            }
            if (pw != null)
                pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}