package com.api.controller;

import com.api.message.MessageInfo;
import com.api.result.Result;
import com.api.service.AgentService;
import com.api.entity.AgentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        agentService.createAgent(ip, port, type, survivalTime);
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/agent", method = RequestMethod.GET)
    public Result<List<AgentEntity>> healthyCheck(@RequestParam(required = false) Integer start, @RequestParam(required = false) Integer end) {
        if (end > 500) {
            return Result.result(MessageInfo.USER_PARAM_IS_TOO_BIG, "You xiao zi Bie Tai Guo Fen.");
        }
        List<AgentEntity> agentEntities = agentService.getAgents(start, end);
        return Result.result(agentEntities);
    }
}
