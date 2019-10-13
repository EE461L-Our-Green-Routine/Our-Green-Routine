package com.example.greenroutine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CarbonFootPrint extends AppCompatActivity {

    public Double totalCarbon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_foot_print);
        totalCarbon = 0.0;
    }
    //gets the user input and calculates the foot print based on that
    public void calculateFP(View view){
        String inputString = findViewById(R.id.carbonInput).toString();
        Double value = stringToPoints(inputString);

        totalCarbon += value;
        TextView totalTextView = findViewById(R.id.carbonTotal);

        totalTextView.setText(totalCarbon.toString());
    }

    //TODO: actually do the actual conversion. something is VERY wrong here
    //does the actual conversion
    public Double stringToPoints(String str){
        //this is just a hardcoded output for show purpose
        return 15.0;
    }
}