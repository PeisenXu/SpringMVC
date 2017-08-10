package com.api.service;

import com.common.entity.UserEntity;
import com.common.model.EmailModel;
import com.common.model.UserResponse;
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
