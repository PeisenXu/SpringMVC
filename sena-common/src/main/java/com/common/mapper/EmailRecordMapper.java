package com.common.mapper;

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
    public static final String SQL_INSERT_ACCOUNT_EMAIL = "INSERT INTO account_email (Id, SendUserName, Recipient, Subject, FileUrl, FileName, Message, Status, CreateAtUtc) VALUE (?,?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE_ACCOUNT_EMAIL_STATUS = "UPDATE account_email SET Status = ? WHERE Id = ?";
    //endregion

    //region mapper
    //endregion
}
