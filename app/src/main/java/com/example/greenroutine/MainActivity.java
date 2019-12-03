package com.example.greenroutine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore mFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();
        new tipTask().execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAbout(View view){
        Intent aboutIntent = new Intent(this, AboutPage.class);
        startActivity(aboutIntent);
    }

    public void goToCatsWTR(View view){
        Intent CIntent = new Intent(this, CategoriesPageWTR.class);
        startActivity(CIntent);
    }

    public void goToCatsHTR(View view){
        Intent CIntent = new Intent(this, CategoriesPageHTR.class);
        startActivity(CIntent);
    }

    public void goToFoot(View view){
        Intent FootIntent = new Intent(this, CoolClimate.class);
        startActivity(FootIntent);
    }

    @SuppressLint("StaticFieldLeak")
    public class tipTask extends AsyncTask<ArrayList<String>, Integer, Void> {
        @SafeVarargs
        @Override
        protected final Void doInBackground(ArrayList<String>... arrayLists) {
            setTip();
            return null;
        }
    }
        private void setTip() {
            final CollectionReference colRef = mFirestore.collection("testmp"); //collection of items from database
            colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        int random = (int)(Math.random()*450);
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Log.d("nice", document.getId() + " => " + document.getData());
                            if(random == 0) {
                                setItemName ((String)document.get("description"));
                                setItemDesc((String) document.get("long_description"));
                                break;
                            }
                            random--;
                        }
                    }
                    else {
                        Log.d("notNice", "Error getting documents: ", task.getException());
                    }
                }
            });
        }
    public void setItemName(String name){
        ((TextView)findViewById(R.id.IOTD)).setText(name);
    }
    public void setItemDesc(String desc){
        ((TextView)findViewById(R.id.DOTD)).setText(desc);
    }
}
