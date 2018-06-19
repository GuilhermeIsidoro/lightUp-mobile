package com.br.lightup.requests;

public class UpdateDeviceRequest {

    private Integer id;
    private String name;
    private Integer deviceConsumption;
    private Integer status;

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
}
