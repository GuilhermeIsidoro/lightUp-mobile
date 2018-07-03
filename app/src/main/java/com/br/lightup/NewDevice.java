package com.br.lightup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.br.lightup.interfaces.OnTaskCompleted;
import com.br.lightup.layout.adapters.DeviceAdapter;
import com.br.lightup.requests.NewDeviceRequest;
import com.br.lightup.rest.PostRequest;
import com.br.lightup.returns.GetDeviceReturn;
import com.br.lightup.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class NewDevice extends AppCompatActivity implements OnTaskCompleted {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_device);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.add_device_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        Button add = findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDevice();
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

    public void addDevice() {

        NewDeviceRequest newDevice = new NewDeviceRequest();

        TextInputEditText Vname = (TextInputEditText) findViewById(R.id.devName);
        TextInputEditText Vcons = (TextInputEditText) findViewById(R.id.devCons);

        String name = Vname.getText().toString();
        String cons = Vcons.getText().toString();

        if ((name == null || name.isEmpty()) || (cons == null || cons.isEmpty())) {

            Toast.makeText(getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_LONG).show();

        } else {

            newDevice.setName(name);
            newDevice.setDeviceConsumption(Integer.parseInt(cons));

            String[] params = {Constants.BACK_END_URL + "/addDevice", new Gson().toJson(newDevice)};

            new PostRequest(NewDevice.this).execute(params);
        }
    }

    @Override
    public void callBack(String message) {

        String mensagemFinal = "Falha ao adicionar o dispositivo";
        if (message != null && message != ""){
            Gson gson = new GsonBuilder().create();
            GetDeviceReturn retorno;
            retorno = gson.fromJson(message, GetDeviceReturn.class);
            mensagemFinal = retorno.getMessage();
        }
        Intent home = new Intent(getApplicationContext(), Home.class);
        Toast.makeText(getApplicationContext(), mensagemFinal, Toast.LENGTH_LONG).show();
        startActivity(home);

    }
}
