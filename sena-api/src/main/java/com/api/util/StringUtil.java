package com.api.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sena on 2017/2/28.
 */
public class StringUtil {
    public static String subCompletedSentence(String text, int maxLength) {
        if (text == null)
            return "";
        if (text.length() <= maxLength)
            return text;
        text = text.substring(0, maxLength);
        //查找最后一个结束符：句号、感叹号、问号
        int endIndex = text.length() - 1;
        for (int i = text.length() - 1; i >= 0; i--) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                endIndex = i;
                break;
            }
        }
        text = text.substring(0, endIndex + 1);
        return text;
    }

    /**
     * 判断字符串是否为NULL或空字符串
     *
     * @param s
     * @return
     */
    public static boolean isEmptyOrBlank(String s) {
        return s == null || (s.trim().length() == 0);
    }

    /**
     * 判断字符串不为NULL或空字符串
     *
     * @param s
     * @return
     */
    public static boolean isNotEmptyOrBlank(String s) {
        return s != null && !(s.trim().isEmpty());
    }

    /**
     * 去除字符串中除了字母数字以外的标点符号
     * @param str
     * @return
     */
    public static String replaceStrBlankAndSymbol(String str){
        char[] strs = str.toCharArray();
        String newStr ="";
        for (int i = 0; i < strs.length; i++) {
            if (Character.isLetter(strs[i]) || Character.isDigit(strs[i])){
                newStr+=strs[i];
            }
        }
        return newStr;
    }
    /**
     * 判断字符串是否是邮箱
     * @param s
     * @return
     */
    public static boolean isEmail(String s){
        if(s==null||s.trim().isEmpty())
            return false;
        Pattern p =  Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static String removeEmojis(String s) {
        if (isEmptyOrBlank(s))
            return "";
        return s.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
    }

    /**
     * 将id list转换为字符串
     *
     * 如：'001','002','003'
     * @param ids
     * @return
     * zjj 2016.6.14
     */
    public static String convertIdsToString(List<String> ids){
        if(ids == null || ids.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        for (String id:ids){
            sb.append("'").append(id).append("'").append(",");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    public static String toLowerWithoutSpace(String string) {
        return string.toLowerCase().replace(" ", "");
    }

    /**
     * 组合字符串
     * 用空格分隔
     * @param strs
     * @return
     */
    public static String combineWithSpace(String... strs) {
        return combineByChar(' ', strs);
    }

    /**
     * 组合字符串
     * 用下划线分隔
     * @param strs
     * @return
     */
    public static String combineWithUnderline(String... strs) {
        return combineByChar('_', strs);
    }

    /**
     * 用指定的字符组合字符串
     * @param c
     * @param strs
     * @return
     */
    public static String combineByChar(char c, String... strs) {
        String result = "";
        for (String str : strs) {
            if (isEmptyOrBlank(str))
                continue;
            if (result == "")
                result += str.trim();
            else
                result += c + str.trim();
        }
        return  result.trim();
    }

    /**
     * 移除字符串中的数字
     * @param str
     * @return
     */
    public static String removeDigits(String str) {
        if (str == null)
            return null;
        return str.replaceAll("\\d", "");
    }

    public static String removeNoDigits(String str) {
        if (str == null)
            return null;
        return str.replaceAll("[^0-9]", "");
    }

    public static boolean isAContainsB (String a, String b){
        a = a.trim().toUpperCase();
        b = b.trim().toUpperCase();
        return  a.contains(b);
    }

    public static String removeSpacesToOne(String str){
        str = str.trim();
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(str);
        String result = m.replaceAll(" ");
        return result;
    }

    /**
     * 判断传来的字符串是否存在集合中
     * @param value
     * @param values
     * @return
     */
    public static boolean isExistInArray(String value, List<String> values) {
        if (StringUtil.isEmptyOrBlank(value))
            return false;
        for (String v : values) {
            if (StringUtil.isEmptyOrBlank(v))
                continue;
            if (v.trim().equalsIgnoreCase(value.trim()))
                return true;
        }
        return false;
    }

    /**
     * 合并两个字符串数组
     * @param fromArray
     * @param toArray
     * @return
     */
    public static List<String> mergeStringArray(List<String> fromArray, List<String> toArray) {
        for (String from : fromArray) {
            if (isEmptyOrBlank(from))
                continue;
            from = from.trim();

            boolean isExist = false;
            for (String to : toArray) {
                if (isEmptyOrBlank(to))
                    continue;
                to = to.trim();

                if (from.equalsIgnoreCase(to)) {
                    isExist = true;
                    break;
                }
            }

            if (!isExist)
                toArray.add(from);
        }

        return toArray;
    }

}