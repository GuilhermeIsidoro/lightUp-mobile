package com.br.lightup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.br.lightup.layout.adapters.DeviceAdapter;
import com.br.lightup.interfaces.OnTaskCompleted;
import com.br.lightup.rest.GetRequest;
import com.br.lightup.returns.GetDeviceReturn;
import com.br.lightup.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnTaskCompleted {

    private TextView mTextMessage;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layout;

    public void loadDevices() {

        //Faço um request GET assíncrono, que fica responsável por criar os elementos na tela.
        new GetRequest(Home.this).execute(Constants.BACK_END_URL + "/getDevice?status=1");
    }

    @Override
    public void callBack(String result) {

        /*if (result == null || result.isEmpty()) {
            //loadDevices();
        }*/

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();

        GetDeviceReturn retorno;

        retorno = gson.fromJson(result, GetDeviceReturn.class);
        if(retorno != null){
            DeviceAdapter adapter = new DeviceAdapter(retorno.getDevices(), this);
            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), "Falha ao buscar os dispostivos", Toast.LENGTH_LONG).show();
        }

    }

    public void createAddDevice() {

        Intent addDevice = new Intent(getApplicationContext(), NewDevice.class);

        startActivity(addDevice);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rec);

        layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        loadDevices();

        FloatingActionButton fab = findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAddDevice();
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.reports){
            Intent reports = new Intent(Home.this, Reports.class);
            startActivity(reports);
        }
        if(id == R.id.options){
            Intent options = new Intent(Home.this, Options.class);
            startActivity(options);
        }
        if(id == R.id.us){
            Intent us = new Intent(Home.this, Us.class);
            startActivity(us);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
