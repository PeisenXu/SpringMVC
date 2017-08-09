package com.api.util;

import com.api.exception.system.Md5CanNotCreateException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by Sena on 2017/3/1.
 */
public class MD5 {
    public static String getMd5(String str) throws Md5CanNotCreateException {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Md5CanNotCreateException();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str = "Sena";
        MessageDigest md = MessageDigest.getInstance("MD5");
        String test = encode(str.getBytes());
        md.update(str.getBytes());
        System.out.println();
        System.out.println(UUID.nameUUIDFromBytes((new BigInteger(1, md.digest()).toString(16)).getBytes()));
    }

    private static char[] ccode = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'S', 'L', 'M', 'N', 'O', 'P',
            'Y', 'Q', 'K', 'T', 'U', 'R', 'W', 'X',
            'Z', 'V', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    public static String encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(ccode[b1 >>> 2]);
                sb.append(ccode[(b1 & 0x3) << 4]);
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(ccode[b1 >>> 2]);
                sb.append(ccode[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(ccode[(b2 & 0x0f) << 2]);
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(ccode[b1 >>> 2]);
            sb.append(ccode[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(ccode[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(ccode[b3 & 0x3f]);
        }
        return sb.toString();
    }
}