package com.sena.dao.impl;

import com.sena.dao.EmailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Sena on 2017/4/12.
 */

@Repository
public class EmailDaoImpl implements EmailDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createEmailRecord(String userName, String emailTo, String fileName, String fileUrl, String subject, String message, String status) {

    }
}
