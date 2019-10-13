package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    public void goToCats(View view){
        Intent CIntent = new Intent(this, CategoriesPage.class);
        startActivity(CIntent);
    }

    public void goToFoot(View view){//currently not working
        Intent FootIntent = new Intent(this, CarbonFootPrint.class);
        startActivity(FootIntent);
    }

    public void searchNear(View view) {
    }
    public void test(View view){

    }
}
