package com.sena.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sena on 2017/4/12.
 */
public class EmailRecordMapper {
    //region Column
    private static String ID = "Id";
    private static String USERNAME = "UserName";
    private static String EMAILTO = "EmailTo";
    private static String FILENAME = "FileName";
    private static String FILEURL = "FileUrl";
    private static String SUBJECT = "Subject";
    private static String MESSAGE = "Message";
    private static String STATUS = "Status";
    private static String CREATEATUTC = "CreateAtUtc";
    //endregion

    //region Sql
    public static final String SQL_SELECT_GETUSER = "SELECT * FROM account_user";
    //endregion

    //region mapper
    //endregion
}
