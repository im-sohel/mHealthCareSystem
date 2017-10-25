package com.learning.jimmy.mhealthcaresystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Doctor_home extends AppCompatActivity implements View.OnClickListener {
   Button b1,b2;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.register);
        t=(TextView)findViewById(R.id.intro);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                startActivity(new Intent(getApplicationContext(),Doctor_login.class));
                break;
            case R.id.register:
                startActivity(new Intent(getApplicationContext(),Doctor_register.class));
                break;
        }
    }
}
