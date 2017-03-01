package com.sena.service;

import com.sena.entity.UserEntity;
import com.sena.model.UserResponse;

import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
public interface AccountService {
    List<UserEntity> getAllUser();

    UserResponse getUserRespone(UserResponse response);
}
