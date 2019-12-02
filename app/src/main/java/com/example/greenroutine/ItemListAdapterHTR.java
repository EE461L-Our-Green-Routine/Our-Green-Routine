package com.example.greenroutine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ItemListAdapterHTR extends RecyclerView.Adapter<ItemListAdapterHTR.MyViewHolder>{
    private Context cont;               //object accessing this adapter
    private ArrayList<Card> data;       //Cards to add to list


    public ItemListAdapterHTR(Context cont, ArrayList<Card> data) {
        this.cont = cont;
        this.data = data;
    }

    /*Class to hold the "views" (card information) for an item in this list*/
    public static class MyViewHolder extends RecyclerView.ViewHolder   {
        ImageView pic;
        TextView item;
        TextView description;

        /*Create a URL to send the user to the correct recycling guide page*/
        public String createURL(String name){
            char nameChars[] = name.toCharArray();
            for(int i=0; i<nameChars.length; i++){
                if((int)nameChars[i]>64&&(int)nameChars[i]<90){
                    nameChars[i]=(char)((int)nameChars[i]+32);
                }
                if(nameChars[i]==' ' || nameChars[i]=='&'){
                    nameChars[i]='-';
                }
            }

            int removed=0;
            for(int i=0; i<nameChars.length; i++){
                if(nameChars[i]=='-'&& i<nameChars.length-1) {
                    if (nameChars[i + 1] == '-') {
                        removed++;
                        int temp = i;
                        for (int j = i + 1; j < nameChars.length; j++) {
                            nameChars[temp] = nameChars[j];
                            temp++;
                        }
                        i--;
                    }
                }
            }
            char shortened[]=new char[nameChars.length-removed];
            for(int i=0; i<nameChars.length-removed; i++){
                shortened[i]=nameChars[i];
            }
            String str = new String(shortened);
            StringBuilder url = new StringBuilder("http://earth911.com/recycling-guide/how-to-recycle-");
            url.append(str);
            url.append("/");
            return url.toString();
        }


        public MyViewHolder(View v) {
            super(v);
            View v1 = v;
            pic = v.findViewById(R.id.pic);
            item = v.findViewById(R.id.item);
            description = v.findViewById(R.id.description);
            /*When the picture for an item is clicked we want to send a user to recycling guide
            webpage corresponding to the item they clicked*/
            pic.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    String name=(String)item.getText();
                    Intent recycleGuide = new Intent(Intent.ACTION_VIEW, Uri.parse(createURL(name))); //Information object for webpage
                    v.getContext().startActivity(recycleGuide);
                }
            });
            /*When an item is clicked we want to send a user to recycling guide
            webpage corresponding to the item they clicked*/
            v1.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    String name=(String)item.getText();
                    Intent recycleGuide = new Intent(Intent.ACTION_VIEW, Uri.parse(createURL(name))); //Information object for webpage
                    v.getContext().startActivity(recycleGuide);

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
        String descript = c.getDescription();
        String name = c.getItem();
        /*Due to the size of the card being finite, we need to cut off descriptions at certain
        limits based on their own size and that of the name*/
        if(name.length()>28){
            descript = " ";
        }
        if(name.length()>=17 && descript.length()>30){
            String d = descript.substring(0,29)+"...";
            descript = d;
        }
        else if(descript.length()>80){
            String d = descript.substring(0,85)+"...";
            descript = d;
        }
        holder.description.setText(descript);
    }

    /*Simple getter class for number of cards*/
    @Override
    public int getItemCount() {
        return data.size();
    }


}
