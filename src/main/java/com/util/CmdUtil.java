package com.util;

import com.learninggenie.common.exception.LearningGenieRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by haoran on 2016/9/30.
 */
public class CmdUtil {
    private static Logger logger = LoggerFactory.getLogger(CmdUtil.class);

    public static void executeCmd(String cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            throw new LearningGenieRuntimeException("Execute cmd failed.", e);
        }
    }

    public static void executeCmd(List<String> cmd) {
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader errStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = errStreamReader.readLine();
            while (line != null) {
                logger.info(line);
                line = errStreamReader.readLine();
            }
            process.waitFor();
        } catch (IOException e) {
            throw new LearningGenieRuntimeException("Execute cmd failed.", e);
        } catch (InterruptedException e) {
            throw new LearningGenieRuntimeException("Execute cmd failed.", e);
        }
    }
}
