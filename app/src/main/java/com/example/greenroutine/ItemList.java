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
    private static final String CATEGORY_NAME = "CATEGORY_NAME";

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

        ArrayList<Card> plastic = new ArrayList<>();
        ArrayList<Card> paper = new ArrayList<>();
        ArrayList<Card> glass = new ArrayList<>();
        ArrayList<Card> metal = new ArrayList<>();
        ArrayList<Card> cardboard= new ArrayList<>();
        Card c1 = new Card(R.drawable.dasani,"dasani", "Plastic water bottle");
        Card c2 = new Card(R.drawable.sunnyd,"sunnyd", "Plastic juice bottle");
        Card c3 = new Card(R.drawable.beer,"beer", "Any brand of glass beer bottle");
        Card c4 = new Card(R.drawable.cereal,"cereal", "Cardboard box");
        Card c5 = new Card(R.drawable.cans,"cans", "Cans made from steel");
        Card c6 = new Card(R.drawable.officepaper,"officepaper", "Paper created for professional use");
        Card c7 = new Card(R.drawable.newspaper,"newspaper", "Thin paper used to distribute news");
        Card c8 = new Card(R.drawable.wine,"wine", "Glass wine bottles");

        plastic.add(c1);
        plastic.add(c2);
        glass.add(c3);
        cardboard.add(c4);
        metal.add(c5);
        paper.add(c6);
        paper.add(c7);
        glass.add(c8);

        String cat = getIntent().getStringExtra(CATEGORY_NAME);
        ItemListAdapter mAdapter;
        switch(cat) {
            case ("Glass Bottles"):
                mAdapter = new ItemListAdapter(this, glass);
                break;
            case ("Plastic Bottles"):
                mAdapter = new ItemListAdapter(this, plastic);
                break;
            case ("Cardboard"):
                mAdapter = new ItemListAdapter(this, cardboard);
                break;
            case ("Metal"):
                mAdapter = new ItemListAdapter(this, metal);
                break;
            case ("Paper"):
                mAdapter = new ItemListAdapter(this, paper);
                break;
            default:
                mAdapter = new ItemListAdapter(this, plastic);
                break;
        }

        recycleView.setAdapter(mAdapter);

    }

    public void sendToItemPage(View view){
        Intent itemIntent = new Intent(this, ItemPage.class);
        startActivity(itemIntent);
    }

}
