package com.common.dao;

import com.common.entity.UserEntity;
import com.common.exception.user.UserRegisterException;

import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
public interface AccountDao {
    List<UserEntity> getAllUser();

    UserEntity getUserByLogin(String userName, String password);

    void createUser(String userName, String hashPassword, String email) throws UserRegisterException;
}
