package com.br.lightup.layout.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.br.lightup.NewDevice;
import com.br.lightup.R;
import com.br.lightup.UpdateDevice;
import com.br.lightup.interfaces.OnTaskCompleted;
import com.br.lightup.model.Device;
import com.br.lightup.rest.PostRequest;
import com.br.lightup.util.Constants;

public class DeviceHolder extends ViewHolder implements OnTaskCompleted {

    public final TextView deviceDescription;
    public final Switch deviceStatsSwitch;

    public Device device;

    public DeviceHolder(final View itemView) {

        super(itemView);

        deviceDescription = (TextView) itemView.findViewById(R.id.texto);
        deviceStatsSwitch = (Switch) itemView.findViewById(R.id.deviceStatus);

        deviceStatsSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] params = {Constants.BACK_END_URL + "/onOffDevice?id=" + device.getId()};

                new PostRequest(DeviceHolder.this).execute(params);
            }
        });

        deviceDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent updateDevice = new Intent(v.getContext(), UpdateDevice.class);

                updateDevice.putExtra("devName", device.getName());
                updateDevice.putExtra("devCons", String.valueOf(device.getDeviceConsumption()));
                updateDevice.putExtra("devStat", device.getStatus());
                updateDevice.putExtra("devID", device.getId());

                v.getContext().startActivity(updateDevice);
            }
        });

    }

    @Override
    public void callBack(String result) {

        Toast.makeText(itemView.getContext(), result, Toast.LENGTH_LONG).show();
    }
}
