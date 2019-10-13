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
        getCommits1("agonzales5071", R.id.mpcommit);
        getIssues("agonzales5071", R.id.mpIssues);
        getTests("agonzales5071", R.id.mptest);

        getCommits1("ElginAllen", R.id.mpcommit1);
        getIssues("ElginAllen", R.id.mpIssues1);
        getTests("ElginAllen", R.id.mptest1);

        getCommits2("Djadih", R.id.mpcommit2);
        getIssues("Djadih", R.id.mpIssues2);
        getTests("Djadih", R.id.mptest2);

        getCommits2("mpontikes", R.id.mpcommit3);
        getIssues("mpontikes", R.id.mpIssues3);
        getTests("mpontikes", R.id.mptest3);

        getCommits1("zsisti", R.id.mpcommit4);
        getIssues("zsisti", R.id.mpIssues4);
        getTests("zsisti", R.id.mptest4);
    }
    public void getCommits1(final String user, final int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/commits?page=1&author=" + user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       int out = response.split("\"commit\"").length -1;
                        ((TextView)findViewById(id)).setText(Integer.toString(out) + " commits");
                        ((TextView)findViewById(R.id.totalCommits)).setText(Integer.toString(exterctNumber(R.id.totalCommits)+out)+" Total Commits");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((TextView)findViewById(id)).setText("ERR commits");
            }
        });
        queue.add(stringRequest);
    }
    public void getCommits2(final String user, final int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/commits?page=2&author=" + user;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int out = 30 + response.split("\"commit\"").length -1;
                        ((TextView)findViewById(id)).setText(Integer.toString(out) + " commits");
                        ((TextView)findViewById(R.id.totalCommits)).setText(Integer.toString(exterctNumber(R.id.totalCommits)+out)+" Total Commits");
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
                        ((TextView)findViewById(R.id.totalIssues)).setText(Integer.toString(exterctNumber(R.id.totalIssues)+out)+" Total Issues");
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
                        ((TextView)findViewById(R.id.totalTests)).setText(Integer.toString(exterctNumber(R.id.totalTests)+Integer.parseInt(response))+" Total Tests");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((TextView)findViewById(id)).setText("ERR tests");
            }
        });
        queue.add(stringRequest);
    }
    public void getAllCommits(final int id){
            int pf = exterctNumber(R.id.mpcommit) + exterctNumber(R.id.mpcommit2) + exterctNumber(R.id.mpcommit3) + exterctNumber(R.id.mpcommit4) + exterctNumber(R.id.mpcommit4);
            ((TextView)findViewById(id)).setText(Integer.toString(pf) + " total commits");
    }
    public int exterctNumber(int ind){
        String up = (String) ((TextView)findViewById(ind)).getText();
        up = up.substring(0,up.indexOf(" "));
        return Integer.parseInt(up);
    }
    public void getAllIssues(final int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/issues?state=all";
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
    public void getAllTests(final int id){
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
                        response = response.substring(response.indexOf("total")+6);
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
