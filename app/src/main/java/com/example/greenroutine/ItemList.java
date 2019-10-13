package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class ItemList extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        // use a linear layout manager
        layManager = new LinearLayoutManager(this);
        layManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view_items);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layManager);

        ArrayList<Card> cards = new ArrayList<>();
        Card c1 = new Card(R.drawable.dasani,"dasani", "Plastic water bottle");
        Card c2 = new Card(R.drawable.sunnyd,"sunnyd", "Plastic juice bottle");
        Card c3 = new Card(R.drawable.beer,"beer", "Any brand of glass beer bottle");
        Card c4 = new Card(R.drawable.cereal,"cereal", "Cardboard box");
        Card c5 = new Card(R.drawable.cans,"cans", "Cans made from steel");
        Card c6 = new Card(R.drawable.officepaper,"officepaper", "Paper created for professional use");
        Card c7 = new Card(R.drawable.newspaper,"newspaper", "Thin paper used to distribute news");
        Card c8 = new Card(R.drawable.wine,"wine", "Glass wine bottles");

        cards.add(c1);
        cards.add(c2);
        cards.add(c3);
        cards.add(c4);
        cards.add(c5);
        cards.add(c6);
        cards.add(c7);
        cards.add(c8);

        ItemListAdapter mAdapter = new ItemListAdapter(this, cards);
        recycleView.setAdapter(mAdapter);

    }

    public void sendToItemPage(View view){
        Intent itemIntent = new Intent(this, ItemPage.class);
        startActivity(itemIntent);
    }

}
