package com.api.controller;

import com.common.entity.UserEntity;
import com.api.message.MessageInfo;
import com.common.model.EmailModel;
import com.common.model.UserResponse;
import com.api.result.Result;
import com.api.service.AccountService;
import com.common.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sena on 2017/2/28.
 */
@Controller
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView healthyCheck() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/jsonHealthy", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> jsonHealthy() {
        Date a = TimeUtil.getNow();
        List<UserEntity> users = accountService.getAllUser();
        Date b = TimeUtil.getNow();
        return Result.result((b.getTime() - a.getTime()) + "ms");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result<UserResponse> userLogin(@RequestBody UserResponse response) {
        Result<UserResponse> userResult = accountService.getUserRespone(response);
        return userResult;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> userRegister(@RequestBody UserResponse response) {
        return accountService.createUser(response);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> sendEmail(@RequestBody EmailModel emailModel) {
        return accountService.sendEmail(emailModel);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> error403(@RequestBody EmailModel emailModel) {
        return Result.result(MessageInfo.INTERNAL_SERVER_ERROR, "403.");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> error404(@RequestBody EmailModel emailModel) {
        return Result.result(MessageInfo.INTERNAL_SERVER_ERROR, "404.");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> error500(@RequestBody EmailModel emailModel) {
        return Result.result(MessageInfo.INTERNAL_SERVER_ERROR, "500.");
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/uuid", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getUUID() {
        return Result.result(UUID.randomUUID().toString().toUpperCase());
    }
}
