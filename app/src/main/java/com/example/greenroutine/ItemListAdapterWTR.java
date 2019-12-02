package com.example.greenroutine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ItemListAdapterWTR extends RecyclerView.Adapter<ItemListAdapterWTR.MyViewHolder>{
    private Context cont;                               //object accessing this adapter
    private ArrayList<Card> data;                       //Cards to add to list
    private static String ITEM_NAME = "ITEM_NAME";      //String used to send item name to itemPage
    private static String DESCRIPTION = "DESCRIPTION";  //String used to send description to itemPage
    private static String ID = "ID";                    //String used to send ID to itemPage
    private static Map nameDescrip = new HashMap();     //Maps item names to item descriptions
    private static Map IDMAPGlobal;                     //Maps item names to item ID's


    public ItemListAdapterWTR(ItemListWTR cont, ArrayList<Card> data, Map<String, String> IDMAP ) {
        this.cont = cont;
        this.data = data;
        IDMAPGlobal = IDMAP;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        TextView item;
        TextView description;

        public MyViewHolder(View v) {
            super(v);
            View v1 = v;
            //pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            v1.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent itL = new Intent(v.getContext(), ItemPage.class);
                    String name=(String)item.getText();
                    //itL.putExtra(CATEGORY_NAME, catName );
                    itL.putExtra(ITEM_NAME, name);
                    String iden = (String)IDMAPGlobal.get(name);
                    String descrip = (String)nameDescrip.get(name);
                    if(descrip!=null) itL.putExtra(DESCRIPTION, descrip);
                    else itL.putExtra(DESCRIPTION, description.getText());
                    if(iden!=null) itL.putExtra(ID, iden);
                    else itL.putExtra(ID, "450");
                    v.getContext().startActivity(itL);
                    //pic.getImageAlpha();

                }
            });
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(cont);
        View v = inf.inflate(R.layout.card2, null);
        MyViewHolder h = new MyViewHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card c = data.get(position);
        holder.item.setText(c.getItem());
        //holder.pic.setImageDrawable(c.getPic());
        String name = c.getItem();
        String descript = c.getDescription();

        nameDescrip.put(c.getItem(), c.getDescription());
        /*if(name.length()>28){
            descript = " ";
        }*/
        if(name.length()>=35 && descript.length()>92){
            String d = descript.substring(0,91)+"...";
            descript = d;
        }
        else if(descript.length()>181){
            String d = descript.substring(0,180)+"...";
            descript = d;
        }
        holder.description.setText(descript);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
