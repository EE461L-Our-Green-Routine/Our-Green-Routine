package com.example.greenroutine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CarbonFootPrint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_foot_print);
    }
    //gets the user input and calculates the foot print based on that
    public void calculateFP(View view){
        TextView inputTextView = findViewById(R.id.carbonInput);
        String inputString = inputTextView.getText().toString();
        double value = stringToPoints(inputString);
    }
    //does the actual conversion
    public double stringToPoints(String str){
        //this is just a hardcoded output for show purpose
        return 15;
    }
}
