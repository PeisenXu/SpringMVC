package com.sena.controller;

import com.sena.model.FileInfo;
import com.sena.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/ip", method = RequestMethod.GET, produces = {"text/html;charset=UTF-8"})
    public String getIp(@RequestParam String ip) {
        return fileService.getIp(ip);
    }
}
