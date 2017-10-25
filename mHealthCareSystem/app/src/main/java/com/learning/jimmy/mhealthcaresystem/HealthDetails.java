package com.learning.jimmy.mhealthcaresystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class HealthDetails extends AppCompatActivity implements View.OnClickListener{
    TextView intro;
    EditText mobileEt;
    Button account;
    private com.android.volley.RequestQueue requestQueue;

    //private static final String URL="http://192.168.43.179/mhcsystem/health_access2.php";
   private static final String URL="https://mhcs.000webhostapp.com/mhcsystem/health_access2.php";

    private StringRequest request;
    Button dhome;
    TextView fullnameTv,ageTv,genderTv,mobileTv,addressTv,outintro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_details);
        intro = (TextView) findViewById(R.id.intro);
        mobileEt = (EditText) findViewById(R.id.mobile);
        account = (Button) findViewById(R.id.account);
        fullnameTv=(TextView)findViewById(R.id.fullname);
        ageTv=(TextView)findViewById(R.id.age);
        genderTv=(TextView)findViewById(R.id.gender);
        mobileTv=(TextView)findViewById(R.id.mobileTv);
        addressTv=(TextView)findViewById(R.id.address);
        outintro=(TextView)findViewById(R.id.outintro);
        dhome=(Button)findViewById(R.id.dochome);


        requestQueue= Volley.newRequestQueue(this);
        dhome.setOnClickListener(this);
        account.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dochome:
                startActivity(new Intent(getApplicationContext(),Doctor_log_home.class));
                break;
            case R.id.account:
            request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        if (jsonObject.names().get(0).equals("empty")) {
                            Toast.makeText(getApplicationContext(), "FillUp the Empty Field!", Toast.LENGTH_SHORT).show();
                        } else if (jsonObject.names().get(0).equals("error")) {
                            Toast.makeText(getApplicationContext(), "Error: Please Enter Registered Mobile No!", Toast.LENGTH_SHORT).show();

                        } else {
                            String bp = jsonObject.getString("bp");
                            String pulse = jsonObject.getString("pulse");
                            String temp = jsonObject.getString("temp");
                            String date = jsonObject.getString("date");
                            String symptoms = jsonObject.getString("symptoms");


                            mobileEt.setVisibility(View.GONE);
                            account.setVisibility(View.GONE);
                            intro.setText("");
                            fullnameTv.setText("Blood Pressure(S/D): " + bp);
                            ageTv.setText("Pulse(b/m): " + pulse);
                            genderTv.setText("Temparature('F): " + temp);
                            addressTv.setText("date(dd/mm/yy): " + date);
                            mobileTv.setText("Symptoms: " + symptoms);
                            outintro.setText("PATIENT HEALTH DETAILS");
                            dhome.setText("GO HOMEPAGE");

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

