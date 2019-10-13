package com.example.greenroutine;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        getCommits("mpontikes", R.id.mpcommit);
        getIssues("mpontikes", R.id.mpIssues);
    }
    public void getCommits(final String user, final int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/commits?author=" + user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       int out = response.split("\"commit\"").length -1;
                        ((TextView)findViewById(id)).setText(Integer.toString(out) + " commits");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((TextView)findViewById(id)).setText("ERR commits");
            }
        });
        queue.add(stringRequest);
    }
    public void getIssues(final String user, final int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/issues?state=all&creator=" + user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int out = response.split("\"repository_url\"").length -1;
                        ((TextView)findViewById(id)).setText(Integer.toString(out) + " issues/PRs");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((TextView)findViewById(id)).setText("ERR issues");
            }
        });
        queue.add(stringRequest);
    }
}
