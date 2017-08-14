package com.api.service.impl;

import com.api.message.MessageInfo;
import com.api.result.Result;
import com.api.service.ServiceService;
import com.common.dao.ServiceDao;
import com.common.entity.ServiceEntity;
import com.common.util.StringUtil;
import com.common.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceDao serviceDao;

    @Override
    public Result<ServiceEntity> getServiceByUId(String uuid) {
        if (StringUtil.isEmptyOrBlank(uuid)) {
            return Result.result(MessageInfo.USER_PARAM_IS_NOT_FOUND, "Uid not found.");
        }
        ServiceEntity service = serviceDao.getServiceByUId(uuid);
        if (service != null) {
            serviceDao.updateServiceStatus(service.getId());
        }
        return Result.result(service);
    }

    @Override
    public void createService(String uuid, String type, String data, String failure) {
        ServiceEntity service = serviceDao.getServiceByUId(uuid);
        if (service != null) {
            serviceDao.updateServiceStatus(service.getId());
        }
        serviceDao.createService(uuid, type, TimeUtil.getUtcNow(), data, failure);
    }

    @Override
    public ServiceEntity getServiceAAA() {
        ServiceEntity service = serviceDao.getServiceByUId("AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA");
        return service;
    }
}
