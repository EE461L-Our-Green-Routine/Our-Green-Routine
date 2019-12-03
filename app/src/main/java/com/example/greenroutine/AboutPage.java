package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class  AboutPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        /* Update Git information for each teammate */
        getGitInfo("agonzales5071", R.id.mpcommit, R.id.mpIssues, R.id.mptest);

        getGitInfo("ElginAllen", R.id.mpcommit1, R.id.mpIssues1, R.id.mptest1);

        getGitInfo("Djadih", R.id.mpcommit2, R.id.mpIssues2, R.id.mptest2);

        getGitInfo("mpontikes", R.id.mpcommit3, R.id.mpIssues3, R.id.mptest3);

        getGitInfo("zsisti", R.id.mpcommit4, R.id.mpIssues4, R.id.mptest4);
    }

    public void getGitInfo(final String user, final int idC, final int idI, final int idT){
        APICallFactory factory = new GitHubAPICallFactory(this,user,idT);
        APICall call = factory.getCall("test");
        call.getData("https://api.github.com/repos/mpontikes/Our-Green-Routine/contents/app/src/test.txt");
        factory = new GitHubAPICallFactory(this,user,idC);
        call = factory.getCall("commit");
        call.getData("https://api.github.com/repos/mpontikes/Our-Green-Routine/commits?author=" + user);
        factory = new GitHubAPICallFactory(this,user,idI);
        call = factory.getCall("issue");
        call.getData("https://api.github.com/repos/mpontikes/Our-Green-Routine/issues?state=all&creator=" + user);
    }

    public int exterctNumber(int ind){
        String up = (String) ((TextView)findViewById(ind)).getText();
        up = up.substring(0,up.indexOf(" "));
        return Integer.parseInt(up);
    }

    public class GitHubAPICallFactory extends APICallFactory{
        String user;
        int id;
        public GitHubAPICallFactory(Context app, String user, int id){
            super(app);
            this.user = user;
            this.id = id;
        }
        public void setId(int id) {
            this.id = id;
        }
        @Override
        APICall createCall(String type) {
            if(type.equals("test")){
                return new GitHubTestAPICall(this.queue,user,id);
            }else if(type.equals("commit")){
                return new GitHubCommitAPICall(this.queue,user,id);
            }
            else if(type.equals("issue")){
                return new GitHubIssuesAPICall(this.queue,user,id);
            }
            return null;
        }
    }

    public class GitHubTestAPICall extends APICall{
        public String user;
        public int id;

        public GitHubTestAPICall(RequestQueue app, String user, int id) {
            super(app);
            this.user = user;
            this.id = id;
        }
        @Override
        public Response.Listener<String> getResponse() {
            return  new Response.Listener<String>() {
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
            };
        }

        @Override
        public Response.ErrorListener getError() {
            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ((TextView)findViewById(id)).setText("ERR tests");
                }
            };
        }
    }

    public class GitHubCommitAPICall extends APICall{
        public String user;
        public int id;
        public String url;
        public int page;

        public GitHubCommitAPICall(RequestQueue app, String user, int id) {
            super(app);
            this.user = user;
            this.id = id;
        }
        @Override
        public Response.Listener<String> getResponse() {
            return  new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int out = response.split("\"commit\"").length -1;
                    if(out == 30){
                        page = page +1;
                        getAPIHelp(url);
                    }else{
                        ((TextView)findViewById(id)).setText(Integer.toString(out + 30*(page-1)) + " commits");
                        ((TextView) findViewById(R.id.totalCommits)).setText(Integer.toString(exterctNumber(R.id.totalCommits) + out + 30 * (page - 1)) + " Total Commits");
                    }
                }
            };
        }

        @Override
        public Response.ErrorListener getError() {
            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ((TextView)findViewById(id)).setText("ERR tests");
                }
            };
        }
        @Override
        public void getData(String url){
            this.url = url;
            this.page = 1;
            getAPIHelp(url);
        }
        private void getAPIHelp(final String url){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url+"&page="+page, this.getResponse(), this.getError());
            queue.add(stringRequest);
        }
    }
    public class GitHubIssuesAPICall extends APICall{
        public String user;
        public int id;
        public String url;
        public int page;

        public GitHubIssuesAPICall(RequestQueue app, String user, int id) {
            super(app);
            this.user = user;
            this.id = id;
        }
        @Override
        public Response.Listener<String> getResponse() {
            return  new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int out = response.split("\"repository_url\"").length -1;
                    if(out == 30){
                        page = page +1;
                        getAPIHelp(url);
                    }else{
                        ((TextView)findViewById(id)).setText(Integer.toString(out + 30*(page-1)) + " issues");
                        ((TextView) findViewById(R.id.totalIssues)).setText(Integer.toString(exterctNumber(R.id.totalIssues) + out + 30 * (page - 1)) + " Total Issues");
                    }
                }
            };
        }

        @Override
        public Response.ErrorListener getError() {
            return new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ((TextView)findViewById(id)).setText("ERR tests");
                }
            };
        }
        @Override
        public void getData(String url){
            this.url = url;
            this.page = 1;
            getAPIHelp(url);
        }
        private void getAPIHelp(final String url){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url+"&page="+page, this.getResponse(), this.getError());
            queue.add(stringRequest);
        }
    }
}
