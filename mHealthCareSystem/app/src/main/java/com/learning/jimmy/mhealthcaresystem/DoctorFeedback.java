package com.learning.jimmy.mhealthcaresystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorFeedback extends AppCompatActivity implements View.OnClickListener{
    TextView introTv;
    EditText mobilet, conditionTv, diagnosisTv, medicinest, remarkst;
    Button feedback,ambulance,home;
    TextView emgt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_feedback);
        introTv=(TextView)findViewById(R.id.intro);

        conditionTv=(EditText)findViewById(R.id.condition);
        diagnosisTv=(EditText)findViewById(R.id.diagnosis);
        medicinest=(EditText)findViewById(R.id.medicines);
        remarkst=(EditText)findViewById(R.id.remarks);
        mobilet=(EditText)findViewById(R.id.mobile);
        ambulance=(Button)findViewById(R.id.ambulance);
         home=(Button)findViewById(R.id.home);
        feedback=(Button)findViewById(R.id.feedback);
         emgt=(TextView)findViewById(R.id.emg);
        feedback.setOnClickListener(this);
        home.setOnClickListener(this);
        ambulance.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ambulance:
                startActivity(new Intent(getApplicationContext(),Ambulance.class));
                break;
            case R.id.home:
                startActivity(new Intent(getApplicationContext(),Doctor_log_home.class));
                break;
            case R.id.feedback:
                String condition=conditionTv.getText().toString();
                String diagnosis=diagnosisTv.getText().toString();
                String medicines=medicinest.getText().toString();
                String remarks=remarkst.getText().toString();
                String mobile=mobilet.getText().toString();

                if(condition.equals("")|| diagnosis.equals("")|| medicines.equals("")|| remarks.equals("")|| mobile.equals("")){
                    Toast.makeText(getApplicationContext(),"Fill all empty fields!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mobile.length()>10 || mobile.length()<10){
                    Toast.makeText(getApplicationContext(),"Mobile no Must Be 10 digits!",Toast.LENGTH_SHORT).show();
                    return;
                }
                String type="save";
                BackgroundFeedback backgroundFeedback=new BackgroundFeedback(this);
                backgroundFeedback.execute(type,mobile,condition,diagnosis,medicines,remarks);
                break;
        }

    }


}

