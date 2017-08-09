package com.api.dao;

/**
 * Created by Sena on 2017/4/12.
 */
public interface EmailDao {
    void createEmailRecord(String id, String userName, String emailTo, String fileName, String fileUrl, String subject, String message, String status);

    void updateEmailStatus(String id, String status);
}
