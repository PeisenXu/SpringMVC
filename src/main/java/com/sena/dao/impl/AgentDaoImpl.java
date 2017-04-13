package com.sena.dao.impl;

import com.sena.dao.AgentDao;
import com.sena.mapper.AgentMapper;
import com.sena.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
