package com.sena.dao.impl;

import com.sena.dao.AccountDao;
import com.sena.entity.UserEntity;
import com.sena.mapper.AccountUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserEntity> testLogin() {
        List<UserEntity> users = new ArrayList<UserEntity>();
        String sql = "select * from account_user";
        try {
            users = jdbcTemplate.query(AccountUserMapper.SQL_SELECT_GETUSER, new Object[]{}, AccountUserMapper.MAPPER_AGENCY);
            return users;
        } catch (Exception e) {
            return null;
        }
    }
}
