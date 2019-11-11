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
    private static String DESCRIPTION = "DESCRIPTION";
    private static ArrayList<String> fullDescrip = new ArrayList<String>();


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
                    boolean found = false;
                    for(String s : fullDescrip){
                        String cmp = s.substring(0, 15);
                        if(cmp.equals(description.getText().toString().substring(0,15))){
                            itL.putExtra(DESCRIPTION, s);
                            found = true;
                        }
                    }
                    if(!found)itL.putExtra(DESCRIPTION, description.getText());
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
                    boolean found = false;
                    for(String s : fullDescrip){
                        String cmp = s.substring(0, 15);
                        if(cmp.equals(description.getText().toString().substring(0,15))){
                            itL.putExtra(DESCRIPTION, s);
                            found = true;
                        }
                    }
                    if(!found)itL.putExtra(DESCRIPTION, description.getText());
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
        String name = c.getItem();
        String descript = c.getDescription();
        fullDescrip.add(descript);
        if(name.length()>=17 && descript.length()>30){
            String d = descript.substring(0,29)+"...";
            descript = d;
        }
        else if(descript.length()>90){
            String d = descript.substring(0,89)+"...";
            descript = d;
        }
        holder.description.setText(descript);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
