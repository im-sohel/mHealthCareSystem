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

public class Doctor_advice extends AppCompatActivity implements View.OnClickListener{
    TextView intro, mobileEt;
    Button init;
    Button homet;
    TextView ambuintroT,nameT,ageT,genderT,pmobileT,addressT;
    private com.android.volley.RequestQueue requestQueue;

    //private static final String URL="http://192.168.43.179/mhcsystem/advice.php";
      private static final String URL="https://mhcs.000webhostapp.com/mhcsystem/advice.php";

    private StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_advice);

        intro = (TextView) findViewById(R.id.intro);
        mobileEt = (EditText) findViewById(R.id.mobile);
        init=(Button)findViewById(R.id.init);

        ambuintroT=(TextView)findViewById(R.id.ambuintro);
        ageT=(TextView)findViewById(R.id.age);
        genderT=(TextView)findViewById(R.id.gender);
        pmobileT=(TextView)findViewById(R.id.pmobile);
        addressT=(TextView)findViewById(R.id.address);
        nameT=(TextView)findViewById(R.id.name);
        homet=(Button)findViewById(R.id.home);
        requestQueue= Volley.newRequestQueue(this);
        init.setOnClickListener(this);
        homet.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                startActivity(new Intent(getApplication(),Patient_log_home.class));
                break;
            case R.id.init:
                String mobile = mobileEt.getText().toString();
                if (mobile.length() > 10 || mobile.length() < 10) {
                    Toast.makeText(getApplicationContext(), "Please Enter 10 digit mobile No!", Toast.LENGTH_SHORT).show();
                    return;
                }
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("empty")) {
                                Toast.makeText(getApplicationContext(), "FillUp the Empty Field!", Toast.LENGTH_SHORT).show();
                            } else if (jsonObject.names().get(0).equals("error")) {
                                Toast.makeText(getApplicationContext(), "No Record Found, Please Send health details!", Toast.LENGTH_SHORT).show();

                            } else {
                                String mobile = jsonObject.getString("mobile");
                                String condition = jsonObject.getString("condition");
                                String diagnosis = jsonObject.getString("diagnosis");
                                String medicines = jsonObject.getString("medicines");
                                String remarks = jsonObject.getString("remarks");

                                mobileEt.setVisibility(View.GONE);
                                init.setVisibility(View.GONE);
                                intro.setText("");
                                nameT.setText("Mobile No: " + mobile);
                                ageT.setText("Health Condition: " + condition);
                                genderT.setText("Diagnosis: " + diagnosis);
                                addressT.setText("Prescribed Medicines: " + medicines);
                                pmobileT.setText("Remarks: " + remarks);
                                ambuintroT.setText("DOCTOR'S ADVICE:");
                                homet.setText("HOME");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("mobile", mobileEt.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(request);
                break;
        }
    }
}
