package com.sena.dao.dao.impl;

import com.sena.dao.dao.AccountDao;
import com.sena.dao.entity.UserEntity;
import com.sena.exception.user.UserRegisterException;
import com.sena.dao.mapper.AccountUserMapper;
import com.sena.dao.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
    public List<UserEntity> getAllUser() {
        try {
            return jdbcTemplate.query(AccountUserMapper.SQL_SELECT_GETUSER, new Object[]{}, AccountUserMapper.MAPPER_AGENCY);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public UserEntity getUserByLogin(String userName, String password) {
        try {
            return jdbcTemplate.queryForObject(AccountUserMapper.SQL_SELECT_USER_BY_INFO, new Object[]{userName, userName, password}, AccountUserMapper.MAPPER_AGENCY);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void createUser(String userName, String hashPassword, String email) throws UserRegisterException {
        try {
            jdbcTemplate.update(AccountUserMapper.SQL_INSERT_USER, userName, hashPassword, email, TimeUtil.getUtcNow(), TimeUtil.getUtcNow());
        } catch (Exception e) {
            logger.info(e+"");
            throw new UserRegisterException();
        }
    }
}