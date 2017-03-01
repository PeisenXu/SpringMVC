package com.sena.controller;

import com.sena.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
        ModelAndView modelAndView = new ModelAndView("view/index");
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/jsonHealthy", method = RequestMethod.GET)
    @ResponseBody
    public List<String> jsonHealthy() {
//        List<UserEntity> users = accountService.getUser();
//        if (users.isEmpty()) {
//            return null;
//        }
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        return list;
    }
}
