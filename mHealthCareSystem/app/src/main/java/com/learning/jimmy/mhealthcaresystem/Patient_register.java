package com.learning.jimmy.mhealthcaresystem;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.Toast;


public class Patient_register extends AppCompatActivity implements View.OnClickListener{
    EditText fname, a, gen, mob, em, addr, pass, cpass;
    Button register,homeBt;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        fname=(EditText)findViewById(R.id.fullname);
        a=(EditText)findViewById(R.id.age);
        gen=(EditText)findViewById(R.id.gender);
        mob=(EditText)findViewById(R.id.mobileno);
        em=(EditText)findViewById(R.id.email);
        addr=(EditText)findViewById(R.id.address);
        cpass=(EditText)findViewById(R.id.cpassword);
        pass=(EditText)findViewById(R.id.password);
        register=(Button)findViewById(R.id.register);
        homeBt=(Button)findViewById(R.id.phome);
        register.setOnClickListener(this);
        homeBt.setOnClickListener(this);

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.phome:
                startActivity(new Intent(getApplicationContext(),Patient_login.class));
                break;
            case R.id.register:
                String fullname=fname.getText().toString();
                String age=a.getText().toString();
                String gender=gen.getText().toString();
                String address=addr.getText().toString();
                String mobile=mob.getText().toString();
                String email=em.getText().toString();
                String password=pass.getText().toString();
                String cpassword=cpass.getText().toString();
                if(fullname.equals("")||age.equals("")||gender.equals("")||email.equals("")||gender.equals("")||address.equals("")||mobile.equals("")||password.equals("")||cpassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Fill Empty fields!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!password.equals(cpassword)){
                    Toast.makeText(getApplicationContext(),"Password Missmatch!",Toast.LENGTH_LONG).show();
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
                String type="register";
                //BackgroundWorker backgroundWorker=new BackgroundWorker(this);
                //backgroundWorker.execute(type,fullname,age,gender,address,mobile,email,password);
                 new PR();
                break;
        }


 }
 class PR{
     PR(){
         new PatientRegister().execute();
     }
 }
 class PatientRegister extends AsyncTask<String, String, String>{

      protected void onPreExecute(){
          super.onPreExecute();
          pDialog = new ProgressDialog(Patient_register.this);
          pDialog.setMessage("Registering Patient...");
          pDialog.setIndeterminate(false);
          pDialog.setCancelable(true);
          pDialog.show();

      }
     @Override
     protected String doInBackground(String... strings) {
         String name=fname.getText().toString();



         return null;
     }
 }


}


