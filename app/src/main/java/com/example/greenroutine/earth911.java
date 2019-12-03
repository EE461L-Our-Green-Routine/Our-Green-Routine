package com.example.greenroutine;

import android.content.res.Resources;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.lang.String;

import androidx.appcompat.app.AppCompatActivity;

public class earth911 extends AppCompatActivity {

    private int limit = 0;
    private String url;
    ArrayList<ArrayList<String>> parameters;
    private ArrayList<Object> result;

    public earth911(ArrayList<ArrayList<String>> parameters){
        url = "https://api.earth911.com/earth911.";

        this.parameters = parameters;
        result = new ArrayList<Object>();
    }

    public JSONArray request(){
        String completeReq = url + parameters.get(0).get(0);
        for(int i = 1; i < parameters.size(); i++){
            completeReq += parameters.get(i).get(0) + parameters.get(i).get(1);
        }
        try {
            AsyncTask name = new dataTask().execute(completeReq);
            JSONObject db = (JSONObject) name.get();
            JSONArray locArray = db.getJSONArray("result");
            return locArray;
        }
        catch (JSONException e) {
            e.printStackTrace();
            throw new ArithmeticException();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ArithmeticException();
        }
    }
    public void parseSearchLoc(JSONArray received){
        ArrayList<String> locNames = new ArrayList<String>();
        ArrayList<String> locDist = new ArrayList<String>();
        ArrayList<Double> locLat = new ArrayList<Double>();
        ArrayList<Double> locLng = new ArrayList<Double>();

        //only store the 5 closest, as the api response is sorted by distance
        limit = (received.length()<5) ? received.length() : 5;
        try {
            for (int i = 0; i < limit; i++) {
                locNames.add(received.getJSONObject(i).getString("description"));
                locDist.add(received.getJSONObject(i).getString("distance"));
                locLat.add(received.getJSONObject(i).getDouble("latitude"));
                locLng.add(received.getJSONObject(i).getDouble("longitude"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            throw new ArithmeticException();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ArithmeticException();
        }

        result.add(locNames);
        result.add(locDist);
        result.add(locLat);
        result.add(locLng);
    }

    public ArrayList<Object> getResult(){
        return result;
    }
}
