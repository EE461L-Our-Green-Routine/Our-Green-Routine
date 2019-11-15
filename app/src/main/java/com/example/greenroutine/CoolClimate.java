package com.example.greenroutine;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CoolClimate extends AppCompatActivity {
    private String footPrint = null;

    private boolean getCarbon(String income, String household, String ZIP){
        String url = "https://apis.berkeley.edu/coolclimate/footprint-defaults?input_location_mode=1&input_location="
                + ZIP +"&input_income="+ income +"&input_size="+ household;

        getResponse(url);

        if(footPrint != null) return false;

        return true;
    }

    public void calculate(View view){
        Spinner income_spin = (Spinner) findViewById(R.id.incomeSpin);
        Spinner size_spin = (Spinner) findViewById(R.id.sizeSpin);
        EditText txtDescription = (EditText) findViewById(R.id.editText2);
        String income = getIncome(income_spin.getSelectedItem().toString());
        String houseSize = getHouseSize(size_spin.getSelectedItem().toString());
        String ZIP = txtDescription.getText().toString();

        getCarbon(income, houseSize, ZIP);



    }
    private void getResponse(String url){
        String URL = url;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String l = response.substring(response.indexOf("result_grand_total"));
                        String actual = l.substring(19, l.indexOf("</result_grand_total"));
                        TextView myAwesomeTextView = (TextView)findViewById(R.id.textView7);
                        myAwesomeTextView.setText(actual);
                        Log.e("Check Response",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Check Error","Error");
                    }
                }
        ){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return new byte[]{};

            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("accept", "application/json");
                map.put("app_id", getString(R.string.coolClimateID));
                map.put("app_key", getString(R.string.coolClimateKey));
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_MAX_RETRIES));
        queue.add(request);
    }
    private String getIncome(String input){
        String ret = "1";
        switch(input){
            case "Average": ret = "1";
            case "Less than $10,000": ret = "2";
            case "$10,000 to $19,999": ret = "3";
            case "$20,000 to $29,999": ret = "4";
            case "$30,000 to $39,999": ret = "5";
            case "$40,000 to $49,999": ret = "6";
            case "$50,000 to $59,999": ret = "7";
            case "$60,000 to $79,999": ret = "8";
            case "$80,000 to $99,999": ret = "9";
            case "$100,000 to $119,999": ret = "10";
            case "$120,000 or more": ret = "11";
        }
        return ret;
    }
    private String getHouseSize(String input){
        String ret = "0";
        switch(input){
            case "Average": ret = "0";
            case "1 person": ret = "1";
            case "2 person": ret = "2";
            case "3 person": ret = "3";
            case "4 person": ret = "4";
            case "5 person or more": ret = "5";
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cool_climate);

        Spinner income = (Spinner) findViewById(R.id.incomeSpin);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.income_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        income.setAdapter(adapter);

        Spinner size = (Spinner) findViewById(R.id.sizeSpin);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this, R.array.houseSize_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        size.setAdapter(adapter);

    }
}
