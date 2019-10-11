package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class DataBaseActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        // use a linear layout manager
        layManager = new LinearLayoutManager(this);
        layManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layManager);

        ArrayList<card> cards = new ArrayList<>();
        card c1 = new card("Hey", "Ho");
        card c2 = new card("Booty", "Butt");
        cards.add(c1);
        cards.add(c2);
        MyAdapter mAdapter = new MyAdapter(this, cards);
        recycleView.setAdapter(mAdapter);


    }

}
