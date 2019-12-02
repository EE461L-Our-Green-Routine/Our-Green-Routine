package com.example.greenroutine;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public abstract class APICallFactory {
    Context context;

    public APICallFactory(Context app) {
        this.context = app;
    }

    public APICall getCall(String type){
        APICall call;
        call = createCall(type);
        return call;
    }
    abstract APICall createCall(String type);
}
