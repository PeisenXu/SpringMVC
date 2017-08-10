package com.api.service.impl;

import com.api.message.MessageInfo;
import com.common.model.UserResponse;
import com.api.result.Result;
import com.common.dao.AccountDao;
import com.common.entity.UserEntity;
import com.common.exception.system.Md5CanNotCreateException;
import com.common.exception.user.UserRegisterException;
import com.common.model.EmailModel;
import com.api.service.AccountService;
import com.api.service.EmailService;
import com.common.util.MD5;
import com.common.util.StringUtil;
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
    @Autowired
    private EmailService emailService;

    public List<UserEntity> getAllUser() {
        return accountDao.getAllUser();
    }

    @Override
    public Result<UserResponse> getUserRespone(UserResponse response) {
        if (StringUtil.isEmptyOrBlank(response.getUserName())) {
            return Result.result(MessageInfo.USER_LOGINNAME_IS_NULL_CODE, "Please input a account.");
        }
        if (StringUtil.isEmptyOrBlank(response.getPassword())) {
            return Result.result(MessageInfo.USER_PASSWORD_IS_NULL_CODE, "Please input a password.");
        }
        //MD5加密密码判断
        String hashPassword = "";
        try {
            hashPassword = MD5.getMd5(response.getPassword());
        } catch (Md5CanNotCreateException e) {
            return Result.result(MessageInfo.SYSTEM_MD5_CREATE_ERRO, "Encryption failed.");
        }
        UserEntity user = accountDao.getUserByLogin(response.getUserName(), hashPassword);
        if (user == null) {
            return Result.result(MessageInfo.USER_LOGIN_ERROR_CODE, "Login failed, please check your account or password.");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setUserName(user.getUserName());
        userResponse.setId(user.getId());
        return Result.result(userResponse);
    }

    @Override
    public Result<String> createUser(UserResponse response) {
        if (StringUtil.isEmptyOrBlank(response.getUserName())) {
            return Result.result(MessageInfo.USER_LOGINNAME_IS_NULL_CODE, "Please input a account.");
        }
        if (StringUtil.isEmptyOrBlank(response.getPassword())) {
            return Result.result(MessageInfo.USER_PASSWORD_IS_NULL_CODE, "Please input a password.");
        }
        if (StringUtil.isEmptyOrBlank(response.getEmail())) {
            return Result.result(MessageInfo.USER_EMAIL_IS_NULL_CODE, "Please input a email.");
        }
        //MD5加密密码
        String hashPassword = "";
        try {
            hashPassword = MD5.getMd5(response.getPassword());
        } catch (Md5CanNotCreateException e) {
            return Result.result(MessageInfo.SYSTEM_MD5_CREATE_ERRO, "Encryption failed.");
        }

        try {
            accountDao.createUser(response.getUserName(), hashPassword, response.getEmail());
        } catch (UserRegisterException e) {
            return Result.result(MessageInfo.SYSTEM_CREATE_USER_ERRO_ERRO, "Cant't Create User.");
        }
        return Result.result(null);
    }

    @Override
    public Result<String> sendEmail(EmailModel emailModel) {
//        emailModel.setTo("75037664@qq.com");
//        emailModel.setMessageHtml("http://china-sen.oss-me-east-1.aliyuncs.com/1.pdf");
//        emailModel.setAttachment("http://china-sen.oss-me-east-1.aliyuncs.com/1.pdf");
//        emailModel.setAttachmentName("gitignore_global.pdf");
//        emailModel.setSubject("主题配置");

        return emailService.sendEmail(emailModel);
    }

}
