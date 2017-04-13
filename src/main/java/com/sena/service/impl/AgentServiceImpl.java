package com.sena.service.impl;

import com.sena.dao.AgentDao;
import com.sena.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sena on 2017/4/13.
 */

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentDao agentDao;

    @Override
    public void createAgenct(String ip, String port, String type, String survivalTime) {
        agentDao.createAgent(ip, port, type, survivalTime);
    }
}
