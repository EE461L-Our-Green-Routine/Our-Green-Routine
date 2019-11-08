package com.example.greenroutine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CarbonFootPrint extends AppCompatActivity {

    public Double totalCarbon;
    SharedPreferences shareP;
    public static final String mP = "preference";
    public static final String username = "username";
    public static final String carbon = "carbon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_foot_print);
        //Context context = getActivity();
        shareP = getSharedPreferences(mP, MODE_PRIVATE);
        if(shareP.contains(username)){
            //set a text view to shareP.getString(username, "")
        }
        if(shareP.contains(carbon)){
            totalCarbon = (double)shareP.getFloat(carbon, (float)0.0);
        }
        else totalCarbon = 0.0;
        TextView totalTextView = findViewById(R.id.carbonTotal);

        totalTextView.setText(totalCarbon.toString());

    }
    //gets the user input and calculates the foot print based on that
    public void calculateFP(View view){
        String inputString = findViewById(R.id.carbonInput).toString();
        Double value = stringToPoints(inputString);

        totalCarbon += value;
        TextView totalTextView = findViewById(R.id.carbonTotal);

        totalTextView.setText(totalCarbon.toString());

        SharedPreferences.Editor edit = shareP.edit();
        edit.putFloat(carbon, (float)((double)totalCarbon));
        edit.commit();

    }

    //TODO: actually do the actual conversion. something is VERY wrong here
    //does the actual conversion
    public Double stringToPoints(String str){
        //this is just a hardcoded output for show purpose

        return 20.0;
    }


}
