package com.learning.jimmy.mhealthcaresystem;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundFeedback extends AsyncTask<String, Void, String>{
    Context context;




    BackgroundFeedback(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type=params[0];



       // String register_url="http://192.168.43.179/mhcsystem/feedback_save.php";

        String register_url="https://mhcs.000webhostapp.com/mhcsystem/feedback_save.php";

        try {

            String mobile=params[1];
            String condition=params[2];
            String diagnosis=params[3];
            String medicines=params[4];
            String remarks=params[5];


            URL url=new URL(register_url);

            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data= URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"
                    +URLEncoder.encode("condition","UTF-8")+"="+URLEncoder.encode(condition,"UTF-8")+"&"
                    +URLEncoder.encode("diagnosis","UTF-8")+"="+URLEncoder.encode(diagnosis,"UTF-8")+"&"
                    +URLEncoder.encode("medicines","UTF-8")+"="+URLEncoder.encode(medicines,"UTF-8")+"&"
                    +URLEncoder.encode("remarks","UTF-8")+"="+URLEncoder.encode(remarks,"UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line=bufferedReader.readLine())!=null){
                result+=line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return null;
    }

    @Override
    protected void onPreExecute() {
        //alertDialog=new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Operation Status!");


    }

    @Override
    protected void onPostExecute(String result) {
        //alertDialog.setMessage(result);
        //alertDialog.show();
        String r=result;
        Toast.makeText(context, r, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
