package com.common.dao;

import com.common.entity.AgentEntity;

import java.util.List;

/**
 * Created by Sena on 2017/4/13.
 */
public interface AgentDao {
    void createAgent(String ip, String port, String type, String survivalTime);

    AgentEntity getAgentByIp(String ip);

    List<AgentEntity> getAgents(Integer start, Integer end);

    void updateAgent(int id, String port, String type, String survivalTime);
}