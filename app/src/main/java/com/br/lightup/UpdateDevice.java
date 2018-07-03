package com.br.lightup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.br.lightup.interfaces.OnTaskCompleted;
import com.br.lightup.model.Device;
import com.br.lightup.requests.UpdateDeviceRequest;
import com.br.lightup.rest.PostRequest;
import com.br.lightup.returns.GetDeviceReturn;
import com.br.lightup.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UpdateDevice extends AppCompatActivity implements OnTaskCompleted {

    private int devID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.update_device);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.update_device_toolbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        Intent updateIntent = getIntent();

        TextInputEditText Vname = (TextInputEditText) findViewById(R.id.devName);
        TextInputEditText Vcons = (TextInputEditText) findViewById(R.id.devCons);
        Switch            Vstat = (Switch)            findViewById(R.id.devStat);

        Vname.setText(updateIntent.getStringExtra("devName"));
        Vcons.setText(updateIntent.getStringExtra("devCons"));

        this.devID = updateIntent.getIntExtra("devID", 0);
        //Vstat.getRootView().setTag(updateIntent.getIntExtra("devID", 0));

        if (updateIntent.getIntExtra("devStat", 1) == 1) {

            Vstat.setChecked(true);

        } else {

            Vstat.setChecked(false);
        }

        Button update = findViewById(R.id.updateButton);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDevice();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent home = new Intent(this, Home.class);
                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void updateDevice() {

        String newDevName = ((TextInputEditText) findViewById(R.id.devName)).getText().toString();
        int    newDevCons = Integer.parseInt(((TextInputEditText) findViewById(R.id.devCons)).getText().toString());
        int    newDevStat = (((Switch) findViewById(R.id.devStat)).isChecked() ? 1 : 0);

        if ((newDevName == null || newDevName.isEmpty())) {

            Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_LONG).show();

        } else {

            UpdateDeviceRequest newDevice = new UpdateDeviceRequest();

            newDevice.setName(newDevName);
            newDevice.setDeviceConsumption(newDevCons);
            newDevice.setStatus(newDevStat);
            newDevice.setId(this.devID);

            String[] params = {Constants.BACK_END_URL + "/updateDevice", new Gson().toJson(newDevice)};

            new PostRequest(UpdateDevice.this).execute(params);
        }
    }

    @Override
    public void callBack(String result) {

        String mensagemFinal = "Falha ao atualizar o dispositivo";
        if (result != null && result != ""){
            Gson gson = new GsonBuilder().create();
            GetDeviceReturn retorno;
            retorno = gson.fromJson(result, GetDeviceReturn.class);
            mensagemFinal = retorno.getMessage();
        }
        Intent home = new Intent(getApplicationContext(), Home.class);
        Toast.makeText(getApplicationContext(), mensagemFinal, Toast.LENGTH_LONG).show();
        startActivity(home);
    }
}
