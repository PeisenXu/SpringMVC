package com.api.service;

import com.api.entity.AgentEntity;

import java.util.List;

/**
 * Created by Sena on 2017/4/13.
 */
public interface AgentService {
    void createAgent(String ip, String port, String type, String survivalTime);

    List<AgentEntity> getAgents(Integer start, Integer end);
}
