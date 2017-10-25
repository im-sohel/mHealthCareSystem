package com.learning.jimmy.mhealthcaresystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Doctor_log_home extends AppCompatActivity  implements View.OnClickListener{
    Button detailsBt, healthBt, adviceBt, logoutBt,appHome;
    TextView introTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_log_home);
        detailsBt = (Button) findViewById(R.id.details);
        healthBt = (Button) findViewById(R.id.ihealth);
        adviceBt = (Button) findViewById(R.id.iadvice);
        logoutBt = (Button) findViewById(R.id.ilogout);
        introTv = (TextView) findViewById(R.id.iintro);
        detailsBt.setOnClickListener(this);
        healthBt.setOnClickListener(this);
        adviceBt.setOnClickListener(this);
        logoutBt.setOnClickListener(this);

        appHome=(Button)findViewById(R.id.apphome);
        appHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.details:
                startActivity(new Intent(getApplicationContext(), PatientDetails.class));
                break;

            case R.id.ihealth:
                startActivity(new Intent(getApplicationContext(), HealthDetails.class));
                break;

            case R.id.iadvice:
                startActivity(new Intent(getApplicationContext(), DoctorFeedback.class)); //NEED MODIFICATION
                break;

            case R.id.ilogout:
                startActivity(new Intent(getApplicationContext(), ActivityHomePage.class));
                break;
            case R.id.apphome:
                startActivity(new Intent(getApplication(), ActivityHomePage.class));
                break;
        }
    }
}
