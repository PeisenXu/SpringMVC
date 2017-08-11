package com.common.util;

import java.sql.*;

/**
 * Created by Sena on 2017/8/11.
 */
public class DBUtil {
    /**
     * 判断指定的结果集中是否有该列
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        if (meta == null)
            return false;

        int columns = meta.getColumnCount();
        for (int i = 1; i <= columns; i++) {
            if (columnName.equals(meta.getColumnName(i))) {
                return true;
            }
        }
        return false;
    }

    public static String getString(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getString(columnName);
    }

    public static boolean getBoolean(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return false;
        return rs.getBoolean(columnName);
    }

    public static Byte getByte(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getByte(columnName);
    }

    public static byte[] getBytes(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getBytes(columnName);
    }

    public static Integer getInt(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getInt(columnName);
    }

    public static int getIntOrDefault(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return 0;
        return rs.getInt(columnName);
    }

    public static Date getDate(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getDate(columnName);
    }

    public static Time getTime(ResultSet rs, String columnName) throws SQLException {
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getTime(columnName);
    }

    public static Timestamp getTimestamp(ResultSet rs, String columnName) throws SQLException{
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getTimestamp(columnName);
    }

    public static Double getDouble(ResultSet rs, String columnName) throws SQLException {
        if (!hasColumn(rs, columnName))
            return null;
        return rs.getDouble(columnName);
    }
}
