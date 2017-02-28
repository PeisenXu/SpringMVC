package com.sena.service.impl;

import com.sena.dao.AccountDao;
import com.sena.entity.UserEntity;
import com.sena.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public List<UserEntity> getUser() {
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = accountDao.testLogin();
        return users;
    }
}
