package com.sena.service.impl;

import com.sena.dao.AccountDao;
import com.sena.entity.UserEntity;
import com.sena.exception.system.Md5CanNotCreateException;
import com.sena.exception.user.UserRegisterException;
import com.sena.message.MessageInfo;
import com.sena.model.EmailModel;
import com.sena.model.UserResponse;
import com.sena.result.Result;
import com.sena.service.AccountService;
import com.sena.service.EmailService;
import com.sena.util.MD5;
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
    public Result<String> sendEmail() {
        EmailModel emailModel = new EmailModel();
        emailModel.setTo("75037664@qq.com");
        emailModel.setMessageHtml("<br/><br/>------------------<br/>\n" +
                "本邮件仅发给指定人员，邮件内容可能涉及保密信息，如果误发贵处请邮件通知发件人并删除此邮件，任何形式的复制、转发或散布本邮件及其内容均属违法行为。。<br/>\n" +
                "The information contained in this communication is intended solely for the use of the individual or entity to whom it is addressed and others authorized to receive it...");
        //emailModel.setAttachment("http://china-sen.oss-me-east-1.aliyuncs.com/1.pdf");
        emailModel.setAttachment("D:\\gitignore_global.txt");
        emailModel.setAttachmentName("gitignore_global.txt");
        emailModel.setSubject("Title");

        emailService.sendEmail(emailModel);
        return Result.result(null);
    }

}
