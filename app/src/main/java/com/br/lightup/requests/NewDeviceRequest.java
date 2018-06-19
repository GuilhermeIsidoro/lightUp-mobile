package com.br.lightup.requests;

public class NewDeviceRequest {

    private String name;
    private Integer deviceConsumption;

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

}
