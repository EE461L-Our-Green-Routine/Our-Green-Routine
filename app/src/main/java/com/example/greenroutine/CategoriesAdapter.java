package com.example.greenroutine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{
    private Context cardCont;
    private ArrayList<Card> data;

    public CategoriesAdapter(Context cardCont, ArrayList<Card> data) {
        this.cardCont = cardCont;
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        //ConstraintLayout cL;
        ImageView pic;
        TextView item;
        TextView description;

        public MyViewHolder(View v) {
            super(v);
            View v1 = v;
            v1.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    v.getContext().startActivity(new Intent(v.getContext(), ItemList.class));
                }
            });
            //cL=itemView.findViewById(R.id.recyMain);
            pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            //cL.setOnClickListener(this);
            //pic.setOnClickListener(this);

        }
/*
        @Override
        public void onClick(View view){
            if(view.getId() == R.id.pic){
                System.out.println("YA THING WORKED MAINE");
                //Intent dbIntent = new Intent(super(view), DataBaseActivity.class);
                //startActivity(dbIntent);
            }
        }
    */
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
        holder.pic.setImageDrawable(cardCont.getResources().getDrawable(c.getPic(), null));
        holder.description.setText(c.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}