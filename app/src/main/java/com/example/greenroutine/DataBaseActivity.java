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
        card c1 = new card(R.drawable.dasani,"Dasani", "Plastic water bottle");
        card c2 = new card(R.drawable.sunnyd,"Sunny D", "Plastic juice bottle");
        card c3 = new card(R.drawable.beer,"Beer Bottles", "Any brand of glass beer bottle");
        card c4 = new card(R.drawable.cereal,"Cereal Boxes", "Cardboard box");
        card c5 = new card(R.drawable.cans,"Steel Cans", "Cans made from steel");
        card c6 = new card(R.drawable.officepaper,"Office Paper", "Paper created for professional use");
        card c7 = new card(R.drawable.newspaper,"Newspaper", "Thin paper used to distribute news");
        card c8 = new card(R.drawable.wine,"Wine bottle", "Glass wine bottles");

        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        cards.add(c6);
        cards.add(c7);
        cards.add(c8);

        MyAdapter mAdapter = new MyAdapter(this, cards);
        recycleView.setAdapter(mAdapter);

    }

}
