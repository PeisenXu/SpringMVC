package com.sena.entity;

import java.util.Date;

/**
 * Created by Sena on 2017/4/13.
 */
public class AgentEntity {
    private int id;
    private String ip;
    private String port;
    private String type;
    private String survivalTime;
    private Date createAtUtc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSurvivalTime() {
        return survivalTime;
    }

    public void setSurvivalTime(String survivalTime) {
        this.survivalTime = survivalTime;
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
}
