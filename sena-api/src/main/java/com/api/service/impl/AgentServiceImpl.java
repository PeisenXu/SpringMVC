package com.api.service.impl;

import com.api.service.AgentService;
import com.sena.dao.dao.AgentDao;
import com.sena.dao.entity.AgentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sena on 2017/4/13.
 */

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentDao agentDao;

    @Override
    public void createAgent(String ip, String port, String type, String survivalTime) {
        AgentEntity agent = agentDao.getAgentByIp(ip);
        if (agent == null) {
            agentDao.createAgent(ip, port, type, survivalTime);
        } else {
            agentDao.updateAgent(agent.getId(), port, type, survivalTime);
        }
    }

    @Override
    public List<AgentEntity> getAgents(Integer start, Integer end) {
        if (start != null && start != 0) {
            start = start - 1;
        }
        if (start == null || end == null) {
            start = 0;
            end = 10;
        }
        return agentDao.getAgents(start, end);
    }
}
