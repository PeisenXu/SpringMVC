package com.sena.controller;

import com.sena.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Sena on 2017/4/13.
 */

@Controller
@RequestMapping("/api/v1/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/agent", method = RequestMethod.POST)
    public void healthyCheck(@RequestParam String ip, @RequestParam String port, @RequestParam String type, @RequestParam String survivalTime) {
        agentService.createAgenct(ip, port, type, survivalTime);
    }
}
