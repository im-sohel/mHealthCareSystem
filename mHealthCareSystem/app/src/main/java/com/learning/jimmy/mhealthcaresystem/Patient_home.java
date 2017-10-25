package com.learning.jimmy.mhealthcaresystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
public class Patient_home extends AppCompatActivity implements View.OnClickListener{

    Button reg, log;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        reg=(Button)findViewById(R.id.register);
        reg.setOnClickListener(this);

        log=(Button)findViewById(R.id.login);
        log.setOnClickListener(this);

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.register:
                Intent i1=new Intent(this, Patient_register.class);
                startActivity(i1);
                break;
            case R.id.login:
                Intent i2=new Intent(this, Patient_login.class);
                startActivity(i2);
        }
    }
}
