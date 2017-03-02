package com.sena.controller;

import com.sena.entity.UserEntity;
import com.sena.model.UserResponse;
import com.sena.result.Result;
import com.sena.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Sena on 2017/2/28.
 */
@Controller
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Healthy check url
     */
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView healthyCheck() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/jsonHealthy", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UserEntity>> jsonHealthy() {
        List<UserEntity> users = accountService.getAllUser();
        return Result.result(users);
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
}
