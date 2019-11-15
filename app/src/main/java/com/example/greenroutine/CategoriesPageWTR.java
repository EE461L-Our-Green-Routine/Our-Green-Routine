package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class CategoriesPageWTR extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_page_wtr);

        // use a linear layout manager
        layManager = new LinearLayoutManager(this);
        layManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view_cats);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layManager);

        Resources res = getApplicationContext().getResources();
        String key = res.getString(R.string.earth911);
        Map<String, ArrayList<String>> families = null;
        try{
            families = ItemListWTR.getDatabase(key);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
        if(families!=null) {
            Set<String> categories = families.keySet();

            ArrayList<Card> cards = new ArrayList<>();
            for (String s : categories) {
                String usable = (s.replaceAll("\\s", "")).toLowerCase();
                //String usable = "automotive";
                Card c = new Card(getDrawable(getResources().getIdentifier(usable, "drawable", this.getPackageName())), s, "Category");
                //Card c = new Card(getDrawable((R.drawable.defaultimage)), s, "Category");
                cards.add(c);
            }

            Collections.sort(cards);
                         


            CategoriesAdapterWTR mAdapter = new CategoriesAdapterWTR(this, cards);
            recycleView.setAdapter(mAdapter);
        }
    }

}
