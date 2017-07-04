package com.sena.service;

import com.sena.entity.AgentEntity;

import java.util.List;

/**
 * Created by Sena on 2017/4/13.
 */
public interface AgentService {
    void createAgenct(String ip, String port, String type, String survivalTime);

    List<AgentEntity> getAgents(Integer start, Integer end);
}
