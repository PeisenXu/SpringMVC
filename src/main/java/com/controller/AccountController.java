package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping(value = "/healthy", method = RequestMethod.GET)
    public ModelAndView healthyCheck() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

}
