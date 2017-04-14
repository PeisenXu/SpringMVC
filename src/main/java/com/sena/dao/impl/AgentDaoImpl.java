package com.sena.dao.impl;

import com.sena.dao.AgentDao;
import com.sena.entity.AgentEntity;
import com.sena.mapper.AgentMapper;
import com.sena.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Sena on 2017/4/13.
 */
@Repository
public class AgentDaoImpl implements AgentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createAgent(String ip, String port, String type, String survivalTime) {
        jdbcTemplate.update(AgentMapper.SQL_CRRATE_AGENT, new Object[]{ip, port, type, survivalTime, TimeUtil.getUtcNow()});
    }

    @Override
    public AgentEntity getAgentByIp(String ip) {
        try {
            return jdbcTemplate.queryForObject(AgentMapper.SQL_SELECT_AGENT_BY_IP, new Object[]{ip}, AgentMapper.AGENT_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<AgentEntity> getAgents(Integer start, Integer end) {
        try {
            String sql = AgentMapper.SQL_SELECT_AGENT;
            if (start != null && end != null) {
                sql = sql + " LIMIT" + start + "," + end;
            }
            return jdbcTemplate.query(sql, AgentMapper.AGENT_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateAgent(int id, String port, String type, String survivalTime) {
        jdbcTemplate.update(AgentMapper.SQL_UPDATE_AGENT_BY_ID, new Object[]{port, type, survivalTime, TimeUtil.getUtcNow(), id});
    }
}
