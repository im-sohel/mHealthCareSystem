/*package com.learning.jimmy.mhealthcaresystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Doctor_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
    }
}
*/
package com.learning.jimmy.mhealthcaresystem;


import android.app.Dialog;
import android.net.http.RequestQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Doctor_login extends AppCompatActivity implements View.OnClickListener{
    EditText mobileEt,passwordEt;
    TextView mobileTv,passwordTv, introTv;
    Button loginBt,regBt;
    private com.android.volley.RequestQueue requestQueue;
    //private static final String URL="http://192.168.43.179/mhcsystem/doctor_log.php";

   private static final String URL="https://mhcs.000webhostapp.com/mhcsystem/doctor_log.php";

    private StringRequest request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        mobileEt=(EditText)findViewById(R.id.idmobile);
        passwordEt=(EditText)findViewById(R.id.idpassword);
        loginBt=(Button)findViewById(R.id.login);
        regBt=(Button)findViewById(R.id.reg);
        regBt.setOnClickListener(this);
        loginBt.setOnClickListener(this);
        requestQueue= Volley.newRequestQueue(this);
        mobileTv=(TextView)findViewById(R.id.idmobileintro);
        passwordTv=(TextView)findViewById(R.id.idpassintro);
        introTv=(TextView)findViewById(R.id.idintro);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reg:
                startActivity(new Intent(getApplicationContext(),Doctor_register.class));

                break;
            case R.id.login:
            request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response);
                        if (jsonObject.names().get(0).equals("success")) {
                            Toast.makeText(getApplicationContext(), "Successfully Login!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Doctor_log_home.class));
                        } else if (jsonObject.names().get(0).equals("empty")) {
                            Toast.makeText(getApplicationContext(), "Fill all the fields!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error: Mobile or password missmatch!", Toast.LENGTH_SHORT).show();
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
                    hashMap.put("password", passwordEt.getText().toString());
                    return hashMap;
                }
            };
            requestQueue.add(request);
                break;
        }
    }
}
