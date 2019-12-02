package com.example.greenroutine;

import android.content.Context;
import android.util.Base64;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public abstract class APICall {
    public RequestQueue queue = null;
    public abstract Response.Listener<String> getResponse();
    public abstract Response.ErrorListener getError();
    public APICall(Context app){
        if(queue == null){
            queue = Volley.newRequestQueue(app);
        }
    }
    public void getData(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, getResponse(), getError());
        queue.add(stringRequest);
    }
}
