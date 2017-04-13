package com.sena.dao;

/**
 * Created by Sena on 2017/4/13.
 */
public interface AgentDao {
    void createAgent(String ip, String port, String type, String survivalTime);
}
