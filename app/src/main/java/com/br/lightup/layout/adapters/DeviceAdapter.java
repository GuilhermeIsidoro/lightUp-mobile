package com.br.lightup.layout.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.lightup.R;
import com.br.lightup.layout.holders.DeviceHolder;
import com.br.lightup.model.Device;

import java.util.ArrayList;

public class DeviceAdapter extends Adapter {

    private ArrayList<Device> devices;
    private Context context;


    public DeviceAdapter (ArrayList<Device> devices, Context context) {
        this.devices = devices;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cards, parent, false);

        DeviceHolder holder = new DeviceHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        DeviceHolder holder = (DeviceHolder) viewHolder;

        Device device = devices.get(position);

        holder.deviceName.setText(device.getName());
        holder.deviceConsumption.setText(String.valueOf(device.getDeviceConsumption()) + "W");
        holder.deviceOnSwitch.setChecked((device.getDeviceOn() == 1 ? true : false));

        //Péssimo para uso de memória, mas é o que tem
        holder.device = device;
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }
}
