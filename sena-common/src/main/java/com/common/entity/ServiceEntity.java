package com.common.entity;

import java.util.Date;

/**
 * Created by Sena on 2017/8/11.
 */
public class ServiceEntity {
    private int id;
    private String uuid;
    private String type;
    private Date createAtUtc;
    private String data;
    private int failure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateAtUtc() {
        return createAtUtc;
    }

    public void setCreateAtUtc(Date createAtUtc) {
        this.createAtUtc = createAtUtc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }
}
