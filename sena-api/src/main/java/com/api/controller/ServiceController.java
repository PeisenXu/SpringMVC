package com.api.controller;

import com.api.result.Result;
import com.api.service.ServiceService;
import com.common.entity.ServiceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Result<ServiceEntity> getService(@RequestParam String uId) {
        return serviceService.getServiceByUId(uId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public void createService(@RequestParam String uId, @RequestParam String type, @RequestParam String data, @RequestParam(required = false) String failure) {
        serviceService.createService(uId, type, data, failure);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ServiceEntity getService() {
        return serviceService.getServiceAAA();
    }
}
