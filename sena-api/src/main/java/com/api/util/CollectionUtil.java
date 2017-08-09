package com.api.util;


import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 集合操作工具类
 * Created by Sena on 2016/8/26.
 */
public class CollectionUtil {
    /**
     * 按照指定字段对list进行排序
     *
     * @param targetList 目标排序List
     * @param sortField  排序字段(实体类属性名)
     * @param fieldType  排序字段类型
     * @param sortMode   排序方式（asc or  desc）
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> void sort(List<T> targetList, final String sortField, final Class<?> fieldType, final String sortMode) {

        if (StringUtil.isEmptyOrBlank(sortField))
            return;
        Collections.sort(targetList, new Comparator() {
            public int compare(Object obj1, Object obj2) {
                int retVal;
                try {
                    //首字母转大写
                    String newStr = sortField.substring(0, 1).toUpperCase() + sortField.replaceFirst("\\w", "");
                    String methodStr = "get" + newStr;

                    Method method1 = ((T) obj1).getClass().getMethod(methodStr);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr);
                    Object result1 = method1.invoke(obj1);
                    Object result2 = method2.invoke(obj2);
                    //如果该字段没有值，则按照空字符串进行排序
                    String value1 = result1 == null ? "" : result1.toString();
                    String value2 = result2 == null ? "" : result2.toString();
                    int intValue1 = 0;
                    int intValue2 = 0;
                    long longValue1 = 0;
                    long longValue2 = 0;
                    //如果是整数类型，排序方式不同
                    if (fieldType == Integer.class) {
                        intValue1 = result1 == null ? 0 : Integer.parseInt(value1);
                        intValue2 = result2 == null ? 0 : Integer.parseInt(value2);
                    } else if (fieldType == Double.class) {
                        //将double类型的值放大100倍比较，更精确
                        intValue1 = result1 == null ? 0 : (int)(Double.valueOf(value1) * 100);
                        intValue2 = result2 == null ? 0 : (int)(Double.valueOf(value2) * 100);
                    } else if (fieldType == Date.class) {
                        longValue1 = result1 == null ? 0 : TimeUtil.parseDate(value1).getTime() / 1000;
                        longValue2 = result2 == null ? 0 : TimeUtil.parseDate(value2).getTime() / 1000;
                    }

                    if (sortMode != null && "desc".equalsIgnoreCase(sortMode)) {
                        if (fieldType == Integer.class || fieldType == Double.class) {    // 倒序
                            retVal = intValue2 - intValue1;
                        } else if (fieldType == Date.class) {
                            retVal = (int)(longValue2 - longValue1);
                        } else {
                            retVal = value2.compareTo(value1);
                        }

                    } else {                                // 正序
                        if (fieldType == Integer.class || fieldType == Double.class) {
                            retVal = intValue1 - intValue2;
                        } else if (fieldType == Date.class) {
                            retVal = (int)(longValue1 - longValue2);
                        } else {
                            retVal = value1.compareTo(value2);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
                return retVal;
            }
        });
    }

    public static <T> void NewSort(List<T> targetList, final String sortField) {
        if (StringUtil.isEmptyOrBlank(sortField))
            return;
        Collections.sort(targetList, new Comparator() {
            public int compare(Object obj1, Object obj2) {
                try {
                    //首字母转大写
                    String newStr = sortField.substring(0, 1).toUpperCase() + sortField.replaceFirst("\\w", "");
                    String methodStr = "get" + newStr;

                    Method method1 = ((T) obj1).getClass().getMethod(methodStr);
                    Method method2 = ((T) obj2).getClass().getMethod(methodStr);
                    Object result1 = method1.invoke(obj1);
                    Object result2 = method2.invoke(obj2);
                    //如果该字段没有值，则按照空字符串进行排序
                    String value1 = result1 == null ? "" : result1.toString();
                    String value2 = result2 == null ? "" : result2.toString();
                    char[] arr1=value1.toCharArray();
                    char[] arr2=value2.toCharArray();
                    int i = 0, j =0;
                    while( i < arr1.length && j < arr2.length)
                    {
                        if (Character.isDigit(arr1[i])&&Character.isDigit(arr2[j]))
                        {
                            String s1 = "";
                            String s2 = "";
                            while ( i < arr1.length && Character.isDigit(arr1[i]) )
                            {
                                s1 += arr1[i];
                                i++;
                            }
                            while (j < arr2.length && Character.isDigit(arr2[j]))
                            {
                                s2 += arr2[j];
                                j++;
                            }
                            if ( Integer.parseInt(s1)>Integer.parseInt(s2))
                            {
                                return 1;
                            }
                            if ( Integer.parseInt(s1)<Integer.parseInt(s2))
                            {
                                return -1;
                            }
                        }
                        else
                        {
                            if ( arr1[i] > arr2[j] )
                            {
                                return 1;
                            }
                            if ( arr1[i] < arr2[j] )
                            {
                                return -1;
                            }
                            i++;
                            j++;
                        }
                    }
                    if ( arr1.length == arr2.length )
                    {
                        return 0;
                    }
                    else
                    {
                        return arr1.length > arr2.length? 1: -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        });
    }
}
