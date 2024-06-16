package com.learning.jimmy.mhealthcaresystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Patient_health_entry extends AppCompatActivity implements View.OnClickListener{
     TextView introTv;
    EditText bpEt, pulseEt, weightEt, tempEt, symptomsEt, bgEt,nameEt, dateEt;
    Button saveBt,homeBt;
    private com.android.volley.RequestQueue requestQueue;

    //private static final String URL="http://192.168.43.179/mhcsystem/health_entry.php";
    private static final String URL="https://mhcs.000webhostapp.com/mhcsystem/health_entry.php";
    private StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_health_entry);

        introTv=(TextView)findViewById(R.id.intro);
        bpEt=(EditText)findViewById(R.id.bp);
        pulseEt=(EditText)findViewById(R.id.pulse);
        weightEt=(EditText)findViewById(R.id.weight);
        tempEt=(EditText)findViewById(R.id.temp);
        symptomsEt=(EditText)findViewById(R.id.symptoms);
        bgEt=(EditText)findViewById(R.id.bg);
        nameEt=(EditText)findViewById(R.id.fullname);
        dateEt=(EditText)findViewById(R.id.date);
        saveBt=(Button)findViewById(R.id.save);
        saveBt.setOnClickListener(this);
        homeBt=(Button)findViewById(R.id.home);
        homeBt.setOnClickListener(this);
        requestQueue= Volley.newRequestQueue(this);
 }
    @Override
    public void onClick(View view) {
              switch (view.getId()){
                  case R.id.home:
                      startActivity(new Intent(getApplicationContext(),Patient_log_home.class));
                      break;
                  case R.id.save:
                      String bp=bpEt.getText().toString();
                      String temp=tempEt.getText().toString();
                      String pulse=pulseEt.getText().toString();
                      String weight=weightEt.getText().toString();
                      String blood_group=bgEt.getText().toString();
                      String any_symptoms=symptomsEt.getText().toString();
                      String name=nameEt.getText().toString();
                      String date=dateEt.getText().toString();
                      if(bp.equals("")|| temp.equals("")|| pulse.equals("")|| weight.equals("")|| blood_group.equals("")|| any_symptoms.equals("")||name.equals("")||date.equals("")){
                          Toast.makeText(getApplicationContext(),"Fill all empty fields!",Toast.LENGTH_SHORT).show();
                          return;
                      }
                      if(name.length()<10 || name.length()>10){
                          Toast.makeText(getApplicationContext(),"Please enter 10 digits mobile!",Toast.LENGTH_SHORT).show();
                          return;
                      }
                      String type="health";
                      BackgroundHealthEntry backgroundHealthEntry=new BackgroundHealthEntry(this);
                      backgroundHealthEntry.execute(type,bp,temp,pulse,weight,blood_group,any_symptoms,name,date);
                      break;
              }

    }


}
