package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);
        TextView mpcommit = (TextView)findViewById(R.id.mpcommit);
        //String out = GitConnect.getGitCommit("mpontikes") + " commits";
        //mpcommit.setText(out);

    }
}
