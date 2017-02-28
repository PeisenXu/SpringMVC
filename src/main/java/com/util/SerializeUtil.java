package com.util;

import com.learninggenie.common.exception.LearningGenieRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.ImagingOpException;
import java.io.*;

/**
 * Created by haoran on 2016/10/31.
 */
public class SerializeUtil {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new LearningGenieRuntimeException("Object serialize failed.", e);
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                logger.error("Close stream failed.");
            }
        }
    }

    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new LearningGenieRuntimeException("Object deserialize failed.", e);
        } finally {
            try {
                if (bais != null)
                    bais.close();
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                logger.error("Close stream failed.");
            }
        }
    }
}
