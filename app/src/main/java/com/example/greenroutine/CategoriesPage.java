package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class CategoriesPage extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page);

        // use a linear layout manager
        layManager = new LinearLayoutManager(this);
        layManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view_cats);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layManager);

        ArrayList<Card> cards = new ArrayList<>();
        Card c1 = new Card(R.drawable.beer,"Glass Bottles", "Bottles made from glass");
        Card c2 = new Card(R.drawable.dasani,"Plastic Bottles", "Bottles made from glass");
        Card c3 = new Card(R.drawable.cereal,"Cardboard", "Anything made from cardboard");
        Card c4 = new Card(R.drawable.officepaper,"Paper", "Paper products");


        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);


        CategoriesAdapter mAdapter = new CategoriesAdapter(this, cards);
        recycleView.setAdapter(mAdapter);

    }

}
