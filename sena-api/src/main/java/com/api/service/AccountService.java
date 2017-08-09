package com.api.service;

import com.api.entity.UserEntity;
import com.api.model.EmailModel;
import com.api.model.UserResponse;
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
