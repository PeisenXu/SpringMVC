package com.api.service;

import com.api.result.Result;
import com.common.entity.ServiceEntity;

public interface ServiceService {
    Result<ServiceEntity> getServiceByUId(String uuid);

    void createService(String uuid, String type, String data, String failure);

    ServiceEntity getServiceAAA();
}
