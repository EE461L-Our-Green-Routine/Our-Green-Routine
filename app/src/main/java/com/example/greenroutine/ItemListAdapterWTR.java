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


public class ItemListAdapterWTR extends RecyclerView.Adapter<ItemListAdapterWTR.MyViewHolder>{
    private Context cardCont;
    private ArrayList<Card> data;
    private String catName;
    private static String ITEM_NAME = "ITEM_NAME";
    private static String PICTURE_ID = "PICTURE_ID";
    private static String CATEGORY_NAME = "CATEGORY_NAME";


    public ItemListAdapterWTR(ItemListWTR cardCont, ArrayList<Card> data, String catName ) {
        this.cardCont = cardCont;
        this.data = data;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        //ConstraintLayout cL;
        ImageView pic;
        TextView item;
        TextView description;
        String catName;

        public MyViewHolder(View v, final String catName) {
            super(v);
            View v1 = v;
            pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            pic.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent itL = new Intent(v.getContext(), ItemPage.class);
                    String name=(String)item.getText();
                    itL.putExtra(ITEM_NAME, name);
                    itL.putExtra(CATEGORY_NAME, catName );
                    v.getContext().startActivity(itL);
                    pic.getImageAlpha();

                }
            });
            v1.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent itL = new Intent(v.getContext(), ItemPage.class);
                    String name=(String)item.getText();
                    itL.putExtra(CATEGORY_NAME, catName );
                    itL.putExtra(ITEM_NAME, name);
                    v.getContext().startActivity(itL);
                    pic.getImageAlpha();

                }
            });
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(cardCont);
        View v = inf.inflate(R.layout.card, null);
        MyViewHolder h = new MyViewHolder(v, catName);
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
