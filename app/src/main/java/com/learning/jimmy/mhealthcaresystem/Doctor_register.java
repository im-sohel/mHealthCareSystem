package com.learning.jimmy.mhealthcaresystem;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Doctor_register extends AppCompatActivity implements View.OnClickListener{
    EditText fname, a, gen, mob, em, addr, pass, cpass;
    Button register, homeBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        fname = (EditText) findViewById(R.id.fullname);
        a = (EditText) findViewById(R.id.age);
        gen = (EditText) findViewById(R.id.gender);
        mob = (EditText) findViewById(R.id.mobileno);
        em = (EditText) findViewById(R.id.email);
        addr = (EditText) findViewById(R.id.address);
        cpass = (EditText) findViewById(R.id.cpassword);
        pass = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
        homeBt = (Button) findViewById(R.id.dhome);
        homeBt.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.dhome:
                startActivity(new Intent(getApplicationContext(), Doctor_login.class));
                break;
            case R.id.register:
                String fullname = fname.getText().toString();
                String age = a.getText().toString();
                String gender = gen.getText().toString();
                String address = addr.getText().toString();
                String mobile = mob.getText().toString();
                String email = em.getText().toString();
                String password = pass.getText().toString();
                String cpassword = cpass.getText().toString();
                if (fullname.equals("") || age.equals("") || gender.equals("") ||email.equals("")||gender.equals("") || address.equals("") || mobile.equals("") || password.equals("") || cpassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fill Empty fields!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(cpassword)) {
                    Toast.makeText(getApplicationContext(), "Password Missmatch!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length()<6 ||cpassword.length()<6) {
                    Toast.makeText(getApplicationContext(), "Password Must be 6 charater long!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(mobile.length()<10 || mobile.length()>10){
                    Toast.makeText(getApplicationContext(),"Please Enter 10 digit mobile no!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(email.indexOf("@")!=-1){

                }else{
                    Toast.makeText(getApplicationContext(),"Please enter '@' sign after email username and compplete it!",Toast.LENGTH_LONG).show();
                    return;
                }
                String type = "register";
                BackgroundRegister BackgroundRegister = new BackgroundRegister(this);
                BackgroundRegister.execute(type, fullname, age, gender, address, mobile, email, password);
                break;

        }


    }
}

