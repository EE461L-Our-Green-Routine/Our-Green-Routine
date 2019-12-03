package com.example.greenroutine;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public abstract class APICallFactory {
    public RequestQueue queue = null;
    public APICallFactory(Context app) {
        if(queue == null) {
            queue = Volley.newRequestQueue(app);
        }
    }

    public APICall getCall(String type){
        APICall call;
        call = createCall(type);
        return call;
    }
    abstract APICall createCall(String type);
}
