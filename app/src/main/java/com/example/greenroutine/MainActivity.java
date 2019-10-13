package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


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
    public void test(View view){

    }
}
