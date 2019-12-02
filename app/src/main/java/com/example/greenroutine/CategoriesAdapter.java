package com.example.greenroutine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{
    private Context cardCont;
    private ArrayList<Card> data;
    private static String CATEGORY_NAME = "CATEGORY_NAME";
    private static Class<?> ItemList;


    /*Constructor, uses a boolean to set the ItemList class to be accessed from this adapter */
    CategoriesAdapter(Context cardCont, ArrayList<Card> data, boolean whereOrHow) {
        this.cardCont = cardCont;
        this.data = data;
        ItemList = (whereOrHow) ? ItemListHTR.class : ItemListWTR.class;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder   {
        ImageView pic;
        TextView item;
        TextView description;

        MyViewHolder(View v) {
            super(v);
            pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            pic.setClickable(true);
            pic.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v){
                    Intent cL = new Intent(v.getContext(), ItemList);
                    String category=(String)item.getText();
                    cL.putExtra(CATEGORY_NAME, category);
                    v.getContext().startActivity(new Intent(cL));
                }
            });
            v.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent cL = new Intent(v.getContext(), ItemList);
                    String category=(String)item.getText();
                    cL.putExtra(CATEGORY_NAME, category);
                    v.getContext().startActivity(new Intent(cL));
                }
            });

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(cardCont);
        View v = inf.inflate(R.layout.card, null);
        MyViewHolder h = new MyViewHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card c = data.get(position);
        holder.item.setText(c.getItem());
        holder.pic.setImageDrawable(c.getPic());
        holder.description.setText(c.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}