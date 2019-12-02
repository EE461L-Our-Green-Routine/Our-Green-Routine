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
    private RequestQueue queue = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_about_page);

        /* Update Git information for each teammate */
        getGitInfo("agonzales5071", R.id.mpcommit, R.id.mpIssues, R.id.mptest);

        getGitInfo("ElginAllen", R.id.mpcommit1, R.id.mpIssues1, R.id.mptest1);

        getGitInfo("Djadih", R.id.mpcommit2, R.id.mpIssues2, R.id.mptest2);

        getGitInfo("mpontikes", R.id.mpcommit3, R.id.mpIssues3, R.id.mptest3);

        getGitInfo("zsisti", R.id.mpcommit4, R.id.mpIssues4, R.id.mptest4);
    }


    public void getAPIData(String url, String search, int id, String tag){
        getAPIHelp(url,1,search, id, tag);
    }

    private void getAPIHelp(final String url, final int page, final String search, final int id, final String tag){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+"&page="+page,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int out = response.split(search).length -1;
                        if(out == 30){
                            getAPIHelp(url,page+1,search, id, tag);
                        }else{
                            ((TextView)findViewById(id)).setText(Integer.toString(out + 30*(page-1)) + " " + tag);
                            if(tag.equals("commits")) {
                                ((TextView) findViewById(R.id.totalCommits)).setText(Integer.toString(exterctNumber(R.id.totalCommits) + out + 30 * (page - 1)) + " Total Commits");
                            }
                            if(tag.equals("issues")) {
                                ((TextView) findViewById(R.id.totalIssues)).setText(Integer.toString(exterctNumber(R.id.totalIssues) + out + 30 * (page - 1)) + " Total Issues");
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ((TextView)findViewById(id)).setText("ERR commits");
            }
        });
        queue.add(stringRequest);
    }

    public void getGitInfo(final String user, final int idC, final int idI, final int idT){
        getCommits(user, idC);
        getIssues(user, idI);
        getTests(user, idT);
    }

    /*Get number of commits for a given member */
    private void getCommits(final String user, final int id){
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/commits?author=" + user;
        getAPIData(url, "\"commit\"", id, "commits");
    }

    /*Get number of issues for a given member */
    private void getIssues(final String user, final int id){
        String url ="https://api.github.com/repos/mpontikes/Our-Green-Routine/issues?state=all&creator=" + user;
        getAPIData(url, "\"repository_url\"", id, "issues");
    }

    /*Get number of tests for a given member */
    private void getTests(final String user, final int id){
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
    public int exterctNumber(int ind){
        String up = (String) ((TextView)findViewById(ind)).getText();
        up = up.substring(0,up.indexOf(" "));
        return Integer.parseInt(up);
    }
}
