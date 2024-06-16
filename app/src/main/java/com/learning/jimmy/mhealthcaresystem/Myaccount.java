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

public class Myaccount extends AppCompatActivity implements View.OnClickListener{
    TextView intro;
    EditText mobileEt;
    Button account;
    private com.android.volley.RequestQueue requestQueue;

   // private static final String URL="http://192.168.43.179/mhcsystem/myaccount.php";

      private static final String URL="https://mhcs.000webhostapp.com/mhcsystem/myaccount.php";

    private StringRequest request;
    TextView fullnameTv,ageTv,genderTv,mobileTv,addressTv,outintro;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        intro = (TextView) findViewById(R.id.intro);
        mobileEt = (EditText) findViewById(R.id.mobile);

        account = (Button) findViewById(R.id.account);

        fullnameTv=(TextView)findViewById(R.id.fullname);
        ageTv=(TextView)findViewById(R.id.age);
        genderTv=(TextView)findViewById(R.id.gender);
        mobileTv=(TextView)findViewById(R.id.mobileTv);
        addressTv=(TextView)findViewById(R.id.address);
        outintro=(TextView)findViewById(R.id.outintro);

        home=(Button)findViewById(R.id.patienhome);

        home.setOnClickListener(this);
        account.setOnClickListener(this);


        requestQueue= Volley.newRequestQueue(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.patienhome:
                startActivity(new Intent(getApplicationContext(),Patient_log_home.class));
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
                            String fullname = jsonObject.getString("fullname");
                            String age = jsonObject.getString("age");
                            String gender = jsonObject.getString("gender");
                            String address = jsonObject.getString("address");
                            String mobile = jsonObject.getString("mobile");

                            mobileEt.setVisibility(View.GONE);
                            account.setVisibility(View.GONE);
                            intro.setText("");
                            fullnameTv.setText("Full Name: " + fullname);
                            ageTv.setText("Age: " + age);
                            genderTv.setText("Gender: " + gender);
                            addressTv.setText("Address: " + address);
                            mobileTv.setText("Mobile: " + mobile);
                            outintro.setText("PERSONAL INFORMATION");
                            home.setText("GO HOMEPAGE");

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
