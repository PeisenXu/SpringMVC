package com.api.controller;

import com.api.service.FileService;
import com.sena.dao.model.FileInfo;
import com.sena.dao.util.HttpUtil;
import com.sena.dao.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sena on 2017-07-18.
 */
@Controller
@RequestMapping("/api/v1/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/agent", method = RequestMethod.GET)
    public void getAgentExcel() {
        FileInfo file = fileService.getFile("");
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/ip", method = RequestMethod.GET)
    public Object getIp(HttpServletRequest httpServletRequest, @RequestParam String ip, @RequestParam(required = false) String type) {
        if (StringUtil.isEmptyOrBlank(ip)) {
            ip = HttpUtil.parseIp(httpServletRequest);
        }
        return fileService.getIp(ip, type);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/check/ip", method = RequestMethod.GET)
    public String checkIp(HttpServletRequest httpServletRequest) {
        return HttpUtil.parseIp(httpServletRequest);
    }
}
