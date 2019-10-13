package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        ArrayList<Card> cards = new ArrayList<>();
        Card c1 = new Card(R.drawable.dasani,"Dasani", "Plastic water bottle");
        Card c2 = new Card(R.drawable.sunnyd,"Sunny D", "Plastic juice bottle");
        Card c3 = new Card(R.drawable.beer,"Beer Bottles", "Any brand of glass beer bottle");
        Card c4 = new Card(R.drawable.cereal,"Cereal Boxes", "Cardboard box");
        Card c5 = new Card(R.drawable.cans,"Steel Cans", "Cans made from steel");
        Card c6 = new Card(R.drawable.officepaper,"Office Paper", "Paper created for professional use");
        Card c7 = new Card(R.drawable.newspaper,"Newspaper", "Thin paper used to distribute news");
        Card c8 = new Card(R.drawable.wine,"Wine bottle", "Glass wine bottles");

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
