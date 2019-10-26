package com.example.greenroutine;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class CategoriesPageHTR extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page_htr);

        // use a linear layout manager
        layManager = new LinearLayoutManager(this);
        layManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view_cats);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layManager);

        ArrayList<Card> cards = new ArrayList<>();
      /*  Card c1 = new Card(R.drawable.beer,"Glass Bottles", "Bottles made from glass");
        Card c2 = new Card(R.drawable.dasani,"Plastic Bottles", "Bottles made from glass");
        Card c3 = new Card(R.drawable.cereal,"Cardboard", "Anything made from cardboard");
        Card c4 = new Card(R.drawable.officepaper,"Paper", "Paper products");
        */
        Card c1 = new Card(getDrawable(R.drawable.automotive),"Automotive", "Car parts and accessories");
        Card c2 = new Card(getDrawable(R.drawable.construction),"Construction", "Construction waste and materials");
        Card c3 = new Card(getDrawable(R.drawable.beer),"Glass", "Glass products and materials");
        Card c4 = new Card(getDrawable(R.drawable.dasani),"Plastic", "Plastic products");
        Card c5 = new Card(getDrawable(R.drawable.householdwaste),"Household Waste", "Residential waste not " +
                "applicable to the other categories");
        Card c6 = new Card(getDrawable(R.drawable.cans),"Metal", "Products made from metal");
        Card c7 = new Card(getDrawable(R.drawable.electronics),"Electronics", "Household electronic products");
        Card c8 = new Card(getDrawable(R.drawable.officepaper),"Paper", "Professional and household paper products");
        Card c9 = new Card(getDrawable(R.drawable.household),"Household", "Residential products not applicable " +
                "to the other categories");


        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        cards.add(c6);
        cards.add(c7);
        cards.add(c8);
        cards.add(c9);


        CategoriesAdapterHTR mAdapter = new CategoriesAdapterHTR(this, cards);
        recycleView.setAdapter(mAdapter);

    }

}
