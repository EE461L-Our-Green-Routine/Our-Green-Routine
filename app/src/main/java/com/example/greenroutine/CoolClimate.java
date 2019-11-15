package com.example.greenroutine;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CoolClimate extends AppCompatActivity {
    private String apiIncome;
    private String apiZIP;
    private String apiSize;

    private int getCarbon(){
        return 0;
    }

    public void calculate(View view){
        Spinner income_spin = (Spinner) findViewById(R.id.incomeSpin);
        String income = income_spin.getSelectedItem().toString();
        Spinner size_spin = (Spinner) findViewById(R.id.sizeSpin);
        String houseSize = size_spin.getSelectedItem().toString();


    }
    private String getIncome(String input){
        String ret = "1";
        switch(input){
            case "Average": ret = "1";
            case "Less than $10,000": ret = "2";
            case "$10,000 to $19,999": ret = "3";
            case "$20,000 to $29,999": ret = "4";
            case "$30,000 to $39,999": ret = "5";
            case "$40,000 to $49,999": ret = "6";
            case "$50,000 to $59,999": ret = "7";
            case "$60,000 to $79,999": ret = "8";
            case "$80,000 to $99,999": ret = "9";
            case "$100,000 to $119,999": ret = "10";
            case "$120,000 or more": ret = "11";
        }
        return ret;
    }
    private String getHouseSize(String input){
        String ret = "0";
        switch(input){
            case "Average": ret = "0";
            case "1 person": ret = "1";
            case "2 person": ret = "2";
            case "3 person": ret = "3";
            case "4 person": ret = "4";
            case "5 person or more": ret = "5";
        }
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cool_climate);

        Spinner income = (Spinner) findViewById(R.id.incomeSpin);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.income_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        income.setAdapter(adapter);

        Spinner size = (Spinner) findViewById(R.id.sizeSpin);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this, R.array.houseSize_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        size.setAdapter(adapter);

    }
}
