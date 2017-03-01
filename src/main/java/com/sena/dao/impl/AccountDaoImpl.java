package com.sena.dao.impl;

import com.sena.dao.AccountDao;
import com.sena.entity.UserEntity;
import com.sena.mapper.AccountUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
@Repository
public class AccountDaoImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserEntity> testLogin() {
        try {
            return jdbcTemplate.query(AccountUserMapper.SQL_SELECT_GETUSER, new Object[]{}, AccountUserMapper.MAPPER_AGENCY);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
