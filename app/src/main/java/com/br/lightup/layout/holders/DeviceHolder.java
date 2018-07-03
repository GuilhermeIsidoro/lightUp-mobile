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
import com.br.lightup.returns.GetDeviceReturn;
import com.br.lightup.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DeviceHolder extends ViewHolder implements OnTaskCompleted {

    public final TextView deviceName;
    public final TextView deviceConsumption;
    public final Switch deviceOnSwitch;

    public Device device;

    public DeviceHolder(final View itemView) {

        super(itemView);

        deviceName = (TextView) itemView.findViewById(R.id.deviceName);
        deviceConsumption = (TextView) itemView.findViewById(R.id.deviceConsumption);
        deviceOnSwitch = (Switch) itemView.findViewById(R.id.deviceOnSwitch);

        deviceOnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] params = {Constants.BACK_END_URL + "/onOffDevice?id=" + device.getId()};

                new PostRequest(DeviceHolder.this).execute(params);
            }
        });

        deviceName.setOnClickListener(new View.OnClickListener() {
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
        String mensagemFinal = "Falha ao mudar estado do dispositivo";
        if (result != null && result != "") {
            Gson gson = new GsonBuilder().create();
            GetDeviceReturn retorno;
            retorno = gson.fromJson(result, GetDeviceReturn.class);
            mensagemFinal = retorno.getMessage();
        }
        Toast.makeText(itemView.getContext(),mensagemFinal, Toast.LENGTH_LONG).show();
    }
}
