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
    private Context cont;                                   //object accessing this adapter
    private ArrayList<Card> data;                           //Cards to add to list
    private static String CATEGORY_NAME = "CATEGORY_NAME";  //String used to send category name to itemlist
    private static Class<?> ItemList;                       //class of the itemlist where the user will be sent next


    /*Constructor, uses a boolean to set the ItemList class to be accessed from this adapter */
    protected CategoriesAdapter(Context cont, ArrayList<Card> data, boolean whereOrHow) {
        this.cont = cont;
        this.data = data;
        this.ItemList = (whereOrHow) ? ItemListHTR.class : ItemListWTR.class;
    }

    /*Class to hold the "views" (card information) for an item in this list*/
    protected static class MyViewHolder extends RecyclerView.ViewHolder   {
        ImageView pic;
        TextView item;
        TextView description;

        MyViewHolder(View v) {
            super(v);
            pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            pic.setClickable(true);
            /*When the picture for an item is clicked we want to send a user to the itemList for the category on which they clicked*/
            pic.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v){
                    Intent cL = new Intent(v.getContext(), ItemList);  //Information object for starting the next activity
                    /*Add information to the information object*/
                    String category=(String)item.getText();
                    cL.putExtra(CATEGORY_NAME, category);
                    v.getContext().startActivity(new Intent(cL));
                }
            });
            /*When an item is clicked we want to send a user to the itemList for the category on which they clicked*/
            v.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent cL = new Intent(v.getContext(), ItemList);  //Information object for starting the next activity
                    /*Add information to the information object*/
                    String category=(String)item.getText();
                    cL.putExtra(CATEGORY_NAME, category);
                    v.getContext().startActivity(new Intent(cL));
                }
            });
        }
    }

    /*Before construction of a viewHolder we want to connect it to the card layout we created */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(cont);
        View v = inf.inflate(R.layout.card, null);
        MyViewHolder h = new MyViewHolder(v);
        return h;
    }

    /*Take each field from the Card class corresponding to each viewHolder and set the views inside it with the
     * information from those fields */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card c = data.get(position);
        holder.item.setText(c.getItem());
        holder.pic.setImageDrawable(c.getPic());
        holder.description.setText(c.getDescription());
    }

    /*Simple getter class for number of cards*/
    @Override
    public int getItemCount() {
        return data.size();
    }


}