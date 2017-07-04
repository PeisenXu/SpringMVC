package com.sena.dao;

/**
 * Created by Sena on 2017/4/12.
 */
public interface EmailDao {
    void createEmailRecord(String userName, String emailTo, String fileName, String fileUrl, String subject, String message, String status);
}
