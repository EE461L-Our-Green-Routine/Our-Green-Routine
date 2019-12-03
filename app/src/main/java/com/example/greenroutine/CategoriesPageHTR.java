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

        cards.add(new Card(getDrawable(R.drawable.automotive),"Automotive", "Car parts and accessories"));
        cards.add(new Card(getDrawable(R.drawable.construction),"Construction", "Construction waste and materials"));
        cards.add(new Card(getDrawable(R.drawable.beer),"Glass", "Glass products and materials"));
        cards.add(new Card(getDrawable(R.drawable.dasani),"Plastic", "Plastic products"));
        cards.add(new Card(getDrawable(R.drawable.householdwaste),"Household Waste", "Residential waste not " +
                "applicable to the other categories"));
        cards.add(new Card(getDrawable(R.drawable.cans),"Metal", "Products made from metal"));
        cards.add(new Card(getDrawable(R.drawable.electronics),"Electronics", "Household electronic products"));
        cards.add(new Card(getDrawable(R.drawable.officepaper),"Paper", "Professional and household paper products"));
        cards.add(new Card(getDrawable(R.drawable.household),"Household", "Residential products not applicable " +
                "to the other categories"));



        CategoriesAdapter mAdapter = new CategoriesAdapter(this, cards, true);
        recycleView.setAdapter(mAdapter);

    }

}
