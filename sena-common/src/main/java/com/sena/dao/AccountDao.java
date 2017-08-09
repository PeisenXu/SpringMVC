package com.sena.dao;

import com.sena.entity.UserEntity;
import com.sena.exception.user.UserRegisterException;

import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
public interface AccountDao {
    List<UserEntity> getAllUser();

    UserEntity getUserByLogin(String userName, String password);

    void createUser(String userName, String hashPassword, String email) throws UserRegisterException;
}
