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


public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder>{
    private Context cardCont;
    private ArrayList<Card> data;
    private static String ITEM_NAME = "ITEM_NAME";

    public ItemListAdapter(Context cardCont, ArrayList<Card> data) {
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
            pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            v1.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent itL = new Intent(v.getContext(), ItemPage.class);
                    String name=(String)item.getText();
                    itL.putExtra(ITEM_NAME, name);
                    v.getContext().startActivity(itL);
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
        holder.pic.setImageDrawable(cardCont.getResources().getDrawable(c.getPic(), null));
        holder.description.setText(c.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
