package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

/* Used https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class DataBaseActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        String empty[] = new String[100];
        mAdapter = new MyAdapter(empty);
        recycleView.setAdapter(mAdapter);

    }

}
