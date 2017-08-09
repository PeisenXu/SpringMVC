package com.api.service;

import com.sena.dao.entity.UserEntity;
import com.sena.dao.model.EmailModel;
import com.sena.dao.model.UserResponse;
import com.api.result.Result;

import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
public interface AccountService {
    List<UserEntity> getAllUser();

    Result<UserResponse> getUserRespone(UserResponse response);

    Result<String> createUser(UserResponse response);

    Result<String> sendEmail(EmailModel emailModel);
}
