package com.util;

import java.util.Comparator;

/**
 * Created by haoran on 2016/5/23.
 */
public class StringWithNumberComparator implements Comparator<String> {
    @Override
    public int compare(String x, String y) {
        if (x == null && y == null)
        {
            return 0;
        }
        if (x == null)
        {
            return -1;
        }
        if (y == null)
        {
            return 1;
        }
        int i = 0, j = 0;
        while (i < x.length() && j < y.length())
        {
            if (Character.isDigit(x.charAt(i)) && Character.isDigit(y.charAt(j)))
            {
                String s1 = "", s2 = "";
                while (i < x.length() && Character.isDigit(x.charAt(i)))
                {
                    s1 += x.charAt(i);
                    i++;
                }
                while (j < y.length() && Character.isDigit(y.charAt(j)))
                {
                    s2 += y.charAt(j);
                    j++;
                }
                if (Integer.parseInt(s1) > Integer.parseInt(s2))
                {
                    return 1;
                }
                if (Integer.parseInt(s1) < Integer.parseInt(s2))
                {
                    return -1;
                }
            }
            else
            {
                if (x.charAt(i) > y.charAt(j))
                {
                    return 1;
                }
                if (x.charAt(i) < y.charAt(j))
                {
                    return -1;
                }
                i++;
                j++;
            }
        }
        if (x.length() == y.length())
        {
            return 0;
        }
        else
        {
            return x.length() > y.length() ? 1 : -1;
        }
    }
}