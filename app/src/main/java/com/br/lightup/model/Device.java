package com.br.lightup.model;

import java.util.Date;

public class Device {

    private Integer id;
    private String name;
    private Integer deviceOn;
    private java.util.Date timeOfChange;
    private Double periodConsumption;
    private Long onTimer;
    private Integer deviceConsumption;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeviceOn() {
        return deviceOn;
    }

    public void setDeviceOn(Integer deviceOn) {
        this.deviceOn = deviceOn;
    }

    public Date getTimeOfChange() {
        return timeOfChange;
    }

    public void setTimeOfChange(Date timeOfChange) {
        this.timeOfChange = timeOfChange;
    }

    public Double getPeriodConsumption() {
        return periodConsumption;
    }

    public void setPeriodConsumption(Double periodConsumption) {
        this.periodConsumption = periodConsumption;
    }

    public Long getOnTimer() {
        return onTimer;
    }

    public void setOnTimer(Long onTimer) {
        this.onTimer = onTimer;
    }

    public Integer getDeviceConsumption() {
        return deviceConsumption;
    }

    public void setDeviceConsumption(Integer deviceConsumption) {
        this.deviceConsumption = deviceConsumption;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;


}
