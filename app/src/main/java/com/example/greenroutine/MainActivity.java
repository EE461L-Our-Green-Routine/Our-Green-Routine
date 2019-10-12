package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAbout(View view){
        Intent aboutIntent = new Intent(this, AboutPage.class);
        startActivity(aboutIntent);
    }

    public void goToDB(View view){
        Intent dbIntent = new Intent(this, DataBaseActivity.class);
        startActivity(dbIntent);
    }

    public void searchNear(View view) {
    }
}
