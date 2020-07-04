package com.example.buddytest2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import java.nio.charset.StandardCharsets;

public class BackgroundWorker extends AsyncTask<String,String,String> {

    String result="";
    Context context;
    public BackgroundWorker(Context context){
        this.context=context;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if(type.equals("SigninAsPlayer")){
            try {
                String playerUser = params[1];
                String playerPass = params[2];
                String login_url = "http://bracketsps.com/SigninAsPlayer.php";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String post_data = URLEncoder.encode("playerUser","UTF-8")+"="+URLEncoder.encode(playerUser,"UTF-8")+"&"+
                        URLEncoder.encode("playerPass","UTF-8")+"="+URLEncoder.encode(playerPass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                String line="";
                while ((line = bufferedReader.readLine()) != null ){
                    result = result + line;
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
        }
        else if(type.equals("SigninAsCoach")){
            try {
                String coachUser = params[1];
                String coachPass = params[2];
                String login_url = "http://bracketsps.com/SigninAsCoach.php";
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
                String post_data = URLEncoder.encode("coachUser","UTF-8")+"="+URLEncoder.encode(coachUser,"UTF-8")+"&"+
                        URLEncoder.encode("coachPass","UTF-8")+"="+URLEncoder.encode(coachPass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                String line="";
                while ((line = bufferedReader.readLine()) != null ){
                    result = result + line;
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

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Coach")) {
            Intent coachPro = new Intent(context, ProfileActivity.class);
            context.startActivity(coachPro);
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else if(result.equals("Player")) {
            Intent playerPro = new Intent(context, ProfileActivity.class);
            context.startActivity(playerPro);
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
