package com.br.lightup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Reports extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reports);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.reports_tollbar);
        setSupportActionBar(myToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        Button period_current = findViewById(R.id.period_current);

        period_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent period = new Intent(getApplicationContext(), PeriodCurrent.class);
                startActivity(period);
            }
        });

        Button history = findViewById(R.id.history);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hist = new Intent(getApplicationContext(), History.class);
                startActivity(hist);
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
}
