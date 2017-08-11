package com.common.dao;

import com.common.entity.ServiceEntity;

import java.util.Date;

/**
 * Created by Sena on 2017/8/11.
 */
public interface ServiceDao {
    ServiceEntity getServiceByUId(String uuid);

    void createService(String uuid, String type, Date createAtUtc, String data, String failure);

    void updateServiceStatus(int uuid);
}
