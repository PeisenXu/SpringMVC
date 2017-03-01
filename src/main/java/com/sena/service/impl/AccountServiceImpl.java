package com.sena.service.impl;

import com.sena.dao.AccountDao;
import com.sena.entity.UserEntity;
import com.sena.exception.SenaThrowRunTimeException;
import com.sena.model.UserResponse;
import com.sena.service.AccountService;
import com.sena.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public List<UserEntity> getAllUser() {
        return accountDao.getAllUser();
    }

    @Override
    public UserResponse getUserRespone(UserResponse response) {
        if (StringUtil.isEmptyOrBlank(response.getUserName())) {
            throw new SenaThrowRunTimeException("Please input a account.");
        }
        if (StringUtil.isEmptyOrBlank(response.getPassword())) {
            throw new SenaThrowRunTimeException("Please input a password.");
        }
        UserEntity user = accountDao.getUserByLogin(response.getUserName(), response.getPassword());
        if (user == null) {
            throw new SenaThrowRunTimeException("Login failed, please check your account or password.");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setUserName(user.getUserName());
        userResponse.setId(user.getId());
        return userResponse;
    }


}
