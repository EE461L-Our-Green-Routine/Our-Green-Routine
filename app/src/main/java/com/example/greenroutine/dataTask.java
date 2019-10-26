package com.example.greenroutine;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class dataTask extends AsyncTask<String, Integer, JSONObject> {

    private JSONObject data;

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            InputStream web = new URL(strings[0]).openStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(web, Charset.forName("UTF-8")));
            StringBuilder bld = new StringBuilder();
            int cp;
            while ((cp = read.read()) != -1) {
                bld.append((char) cp);
            }
            data = new JSONObject(bld.toString());
            web.close();
        }
        catch(Exception e){
            System.out.println("Sorry! Something went wrong with DataTask");
        }
        if (data == null){
            System.out.println("Sorry! Something went wrong with DataTask");
        }
        return data;
    }

    @Override
    protected void onPostExecute (JSONObject result){
        
    }
}