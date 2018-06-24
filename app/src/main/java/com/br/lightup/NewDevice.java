package com.br.lightup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.br.lightup.interfaces.OnTaskCompleted;
import com.br.lightup.requests.NewDeviceRequest;
import com.br.lightup.rest.PostRequest;
import com.br.lightup.util.Constants;
import com.google.gson.Gson;

public class NewDevice extends AppCompatActivity implements OnTaskCompleted {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.new_device);

        Button add = findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDevice();
            }
        });
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

        Intent home = new Intent(getApplicationContext(), Home.class);
        startActivity(home);
        Toast.makeText(getApplicationContext(), message.substring(12,51), Toast.LENGTH_LONG).show();
    }
}
