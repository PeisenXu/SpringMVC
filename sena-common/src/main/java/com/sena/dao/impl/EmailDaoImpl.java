package com.sena.dao.dao.impl;

import com.sena.dao.dao.EmailDao;
import com.sena.dao.util.TimeUtil;
import com.sena.dao.mapper.EmailRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Sena on 2017/4/12.
 */

@Repository
public class EmailDaoImpl implements EmailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createEmailRecord(String id, String userName, String emailTo, String fileName, String fileUrl, String subject, String message, String status) {
        jdbcTemplate.update(EmailRecordMapper.SQL_INSERT_ACCOUNT_EMAIL, new Object[]{id, userName, emailTo, subject, fileUrl, fileName, message, status, TimeUtil.getUtcNow()});
    }

    @Override
    public void updateEmailStatus(String id, String status) {
        jdbcTemplate.update(EmailRecordMapper.SQL_UPDATE_ACCOUNT_EMAIL_STATUS, new Object[]{status, id});
    }
}
