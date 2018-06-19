package com.br.lightup.returns;

import com.br.lightup.model.Device;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetDeviceReturn {

    @SerializedName("message")
    private String message;

    @SerializedName("devices")
    private ArrayList<Device> devices;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }
}
