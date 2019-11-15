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
import java.util.HashMap;
import java.util.Map;


public class ItemListAdapterWTR extends RecyclerView.Adapter<ItemListAdapterWTR.MyViewHolder>{
    private Context cardCont;
    private ArrayList<Card> data;
    private String catName;
    private static String ITEM_NAME = "ITEM_NAME";
    //private static String PICTURE_ID = "PICTURE_ID";
    private static String CATEGORY_NAME = "CATEGORY_NAME";
    private static String DESCRIPTION = "DESCRIPTION";
    private static String ID = "ID";
    private static Map nameDescrip = new HashMap();
    private static Map IDMAPGlobal;


    public ItemListAdapterWTR(ItemListWTR cardCont, ArrayList<Card> data, String catName, Map<String, String> IDMAP ) {
        this.cardCont = cardCont;
        this.data = data;
        IDMAPGlobal = IDMAP;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        //ConstraintLayout cL;
        //ImageView pic;
        TextView item;
        TextView description;
        String catName;

        public MyViewHolder(View v, final String catName) {
            super(v);
            View v1 = v;
            //pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            /*pic.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent itL = new Intent(v.getContext(), ItemPage.class);
                    String name=(String)item.getText();
                    itL.putExtra(ITEM_NAME, name);
                    itL.putExtra(CATEGORY_NAME, catName );
                    String descrip = (String)nameDescrip.get(name);
                    if(descrip!=null) itL.putExtra(DESCRIPTION, descrip);
                    else itL.putExtra(DESCRIPTION, description.getText());
                    v.getContext().startActivity(itL);
                    pic.getImageAlpha();

                }
            });*/
            v1.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    Intent itL = new Intent(v.getContext(), ItemPage.class);
                    String name=(String)item.getText();
                    itL.putExtra(CATEGORY_NAME, catName );
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
        LayoutInflater inf = LayoutInflater.from(cardCont);
        View v = inf.inflate(R.layout.card2, null);
        MyViewHolder h = new MyViewHolder(v, catName);
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
