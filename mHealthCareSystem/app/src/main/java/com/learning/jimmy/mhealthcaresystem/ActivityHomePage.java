package com.learning.jimmy.mhealthcaresystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
public class ActivityHomePage extends AppCompatActivity implements View.OnClickListener{

    Button btn1, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btn1=(Button)findViewById(R.id.patientbutton);
        btn1.setOnClickListener(this);

        btn2=(Button)findViewById(R.id.doctorbutton);
        btn2.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.doctorbutton:
                Intent setIntent=new Intent(this, Doctor_home.class);
                startActivity(setIntent);
                break;
            case R.id.patientbutton:
                Intent setIntent2=new Intent(this, Patient_home.class);
                startActivity(setIntent2);
                break;
        }
    }
}
