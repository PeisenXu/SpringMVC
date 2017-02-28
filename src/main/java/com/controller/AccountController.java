package com.controller;

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
        List<String> stringList = new ArrayList<String>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");

        return stringList;
    }
}
