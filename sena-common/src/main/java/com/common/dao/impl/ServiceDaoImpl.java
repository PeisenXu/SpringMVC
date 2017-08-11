package com.common.dao.impl;

import com.common.dao.ServiceDao;
import com.common.entity.ServiceEntity;
import com.common.mapper.ServiceMapper;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class ServiceDaoImpl implements ServiceDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ServiceEntity getServiceByUId(String uuid) {
        try {
            return jdbcTemplate.queryForObject(ServiceMapper.SQL_SELECT_ACCOUNT_SERVICE, new Object[]{uuid}, ServiceMapper.MAPPER_AGENCY);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void createService(String uuid, String type, Date createAtUtc, String data, String failure) {
        jdbcTemplate.update(ServiceMapper.SQL_INSERT_ACCOUNT_SERVICE, new Object[]{uuid, type, createAtUtc, data, failure});
    }

    @Override
    public void updateServiceStatus(int id) {
        jdbcTemplate.update(ServiceMapper.SQL_UPDATE_ACCOUNT_SERVICE_STATUS, new Object[]{id});
    }
}
