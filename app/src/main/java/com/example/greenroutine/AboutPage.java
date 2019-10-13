package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
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

        getCommits("agonzales5071", R.id.mpcommit);
        getIssues("agonzales5071", R.id.mpIssues);
        getTests("agonzales5071", R.id.mptest);

        getCommits("ElginAllen", R.id.mpcommit1);
        getIssues("ElginAllen", R.id.mpIssues1);
        getTests("ElginAllen", R.id.mptest1);

        getCommits("Djadih", R.id.mpcommit2);
        getIssues("Djadih", R.id.mpIssues2);
        getTests("Djadih", R.id.mptest2);

        getCommits("mpontikes", R.id.mpcommit3);
        getIssues("mpontikes", R.id.mpIssues3);
        getTests("mpontikes", R.id.mptest3);

        getCommits("zsisti", R.id.mpcommit4);
        getIssues("zsisti", R.id.mpIssues4);
        getTests("zsisti", R.id.mptest4);
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
    public void getTests(final String user, final int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/contents/app/src/test.txt";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.substring(response.indexOf("\"content\":\"")+11);
                        response = response.substring(0,response.indexOf("\""));
                        response = response.replace("\\n", "");
                        response = new String(Base64.decode(response,0));
                        response = response.substring(response.indexOf(user)+user.length()+1);
                        response = response.substring(0, response.indexOf("\n"));
                        ((TextView)findViewById(id)).setText(response + " tests");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((TextView)findViewById(id)).setText("ERR tests");
            }
        });
        queue.add(stringRequest);
    }
}
