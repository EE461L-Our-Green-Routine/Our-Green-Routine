package com.example.greenroutine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.InputStream;
import java.net.URL;
import java.sql.Ref;
import java.util.ArrayList;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class ItemListWTR extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;
    private static final String CATEGORY_NAME = "CATEGORY_NAME";
    private FirebaseFirestore mFirestore;



    //gets list of items in input family returns list of cards
    private ArrayList<Card> getList(final String fam){
        //mDatabase.child(CATEGORY_NAME).child(itemName);
        final CollectionReference colRef = mFirestore.collection("Items"); //collection of items from database
        //DocumentReference docRef = mFirestore.collection("glass").document("glass");

            colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Card> itemsInFam = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("nice", document.getId() + " => " + document.getData());
                                String[] allFams = (String[]) document.get("family_ids");
                                for(String currentID : allFams){
                                    if(currentID.equals(fam)){
                                        String name = (String) document.get("description");
                                        String descript = (String) document.get("long_description");
//                                        String picUrl = (String) document.get("image");
//                                        try {
//                                            InputStream is = (InputStream) new URL(picUrl).getContent();
//                                            Drawable itemPic = Drawable.createFromStream(is, null);
//                                            //not done pic not obtained, card not created or stroed in arraylist
//                                        } catch (Exception e) {
//                                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
//                                        }
                                        // Card item = new Card();
                                    }
                                }
                            }
                        } else {
                            Log.d("notNice", "Error getting documents: ", task.getException());
                        }
                    }
                });
        //
//        String[] link = new String[1];
//        link[0] = "a";
//        if(link[0].equals("")){
//            Toast.makeText(getApplicationContext(),"REEEEEE",Toast.LENGTH_SHORT).show();
//        }
        //Toast.makeText(getApplicationContext(),link[0],Toast.LENGTH_SHORT).show();
        TextView linkText = findViewById(R.id.link);
        //linkText.setText(link);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_wtr);

        // use a linear layout manager
        layManager = new LinearLayoutManager(this);
        layManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view_items);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layManager);

        ArrayList<Card> plastic = new ArrayList<>();
        ArrayList<Card> paper = new ArrayList<>();
        ArrayList<Card> glass = new ArrayList<>();
        ArrayList<Card> metal = new ArrayList<>();
        ArrayList<Card> hhw= new ArrayList<>();
        ArrayList<Card> house= new ArrayList<>();
        ArrayList<Card> automotive= new ArrayList<>();
        ArrayList<Card> electronics= new ArrayList<>();
        Card c1 = new Card(getDrawable(R.drawable.dasani),"Dasani", "Plastic water bottle");
        Card c2 = new Card(getDrawable(R.drawable.sunnyd),"Sunny D", "Plastic juice bottle");
        Card c3 = new Card(getDrawable(R.drawable.beer),"Beer", "Any brand of glass beer bottle");
        Card c4 = new Card(getDrawable(R.drawable.cereal),"Cereal", "Cardboard box");
        Card c5 = new Card(getDrawable(R.drawable.cans),"Cans", "Cans made from steel");
        Card c6 = new Card(getDrawable(R.drawable.officepaper),"Office Paper", "Paper created for professional use");
        Card c7 = new Card(getDrawable(R.drawable.newspaper),"Newspaper", "Thin paper used to distribute news");
        Card c8 = new Card(getDrawable(R.drawable.wine),"Wine", "Glass wine bottles");
        //Card c9 = new Card(R.drawable.alex, "glass", "glass link test");

        plastic.add(c1);
        plastic.add(c2);
        glass.add(c3);
        metal.add(c5);
        paper.add(c6);
        paper.add(c7);
        glass.add(c8);
        //glass.add(c9);
        String cat = getIntent().getStringExtra(CATEGORY_NAME);
        ItemListAdapterWTR mAdapter;
        switch(cat) {
            case ("Glass"):
                mAdapter = new ItemListAdapterWTR(this, glass, cat);
                break;
            case ("Plastic"):
                mAdapter = new ItemListAdapterWTR(this, plastic, cat);
                break;
            /*case ("Cardboard"):
                mAdapter = new ItemListAdapterWTR(this, cardboard);
                break;
            */case ("Metal"):
                mAdapter = new ItemListAdapterWTR(this, metal, cat);
                break;
            case ("Paper"):
                mAdapter = new ItemListAdapterWTR(this, paper, cat);
                break;
            default:
                mAdapter = new ItemListAdapterWTR(this, plastic, cat);
                break;
        }

        recycleView.setAdapter(mAdapter);

    }
    /*
    public void sendToItemPage(View view){
        Intent itemIntent = new Intent(this, ItemPage.class);
        startActivity(itemIntent);
    }*/

}
