package com.learning.jimmy.mhealthcaresystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Patient_log_home extends AppCompatActivity implements View.OnClickListener {
    Button accountBt, healthBt, adviceBt, logoutBt, appHome;
    TextView introTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_log_home);
        accountBt = (Button) findViewById(R.id.account);
        healthBt = (Button) findViewById(R.id.health);
        adviceBt = (Button) findViewById(R.id.advice);
        logoutBt = (Button) findViewById(R.id.logout);
        introTv = (TextView) findViewById(R.id.intro);

        accountBt.setOnClickListener(this);
        healthBt.setOnClickListener(this);
        adviceBt.setOnClickListener(this);
        logoutBt.setOnClickListener(this);

        appHome=(Button)findViewById(R.id.apphome);
        appHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account:
                startActivity(new Intent(getApplicationContext(), Myaccount.class)); //NEED MODIFICATION
                break;

            case R.id.health:
                startActivity(new Intent(getApplicationContext(), Patient_health_entry.class));
                break;

            case R.id.advice:
                startActivity(new Intent(getApplicationContext(), Doctor_advice.class)); //NEED MODIFICATION
                break;

            case R.id.logout:
                startActivity(new Intent(getApplicationContext(), ActivityHomePage.class));
                break;
            case R.id.apphome:
                startActivity(new Intent(getApplication(), ActivityHomePage.class));
                break;
        }
    }
}
