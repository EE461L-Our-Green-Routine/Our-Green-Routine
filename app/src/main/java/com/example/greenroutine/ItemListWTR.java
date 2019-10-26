package com.example.greenroutine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class ItemListWTR extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;
    private static final String CATEGORY_NAME = "CATEGORY_NAME";
    private FirebaseFirestore mFirestore;
    private ArrayList<Card> itemsInFam;


    //parses database to return map containing string
    public static Map<String, ArrayList<String>> getDatabase(String key) throws IOException, JSONException {
        Map<String, ArrayList<String>> out = new HashMap<>();

        JSONObject data_task;
        JSONArray data = null;

        AsyncTask task = new dataTask().execute("https://api.earth911.com/earth911.getFamilies?api_key=" + key);
        try {
            data_task = (JSONObject) task.get();
            data = (JSONArray) data_task.get("result");

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //JSONArray data = (JSONArray)(getData("https://api.earth911.com/earth911.getFamilies?api_key=" + key).get("result"));
        for(int i = 0; i < data.length(); i++){
            JSONObject cat = (JSONObject)data.get(i);
            ArrayList<String> ids = new ArrayList<>();
            String name;
            if(cat.has("material_ids") && cat.has("description")){
                JSONArray idsArray = (JSONArray) cat.get("material_ids"); //gets array of string
//                for (JSONObject cur : idsArray) {//puts array of string into mappable arraylist
//                    ids.add(cur);
//                }

                for (int j = 0; j < idsArray.length(); j++) {
                    ids.add(idsArray.get(j).toString());
                }
                name = (String) cat.get("description");

                out.put(name, ids); //maps key value pair created
            }

        }
        return out;
    }


    public static JSONObject getData(String url) throws IOException, JSONException {
        InputStream web = new URL(url).openStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(web, Charset.forName("UTF-8")));
        StringBuilder bld = new StringBuilder();
        int cp;
        while ((cp = read.read()) != -1) {
            bld.append((char) cp);
        }
        JSONObject data = new JSONObject(bld.toString());
        web.close();
        return data;
    }

    //gets list of items in input family returns list of cards
    private void makeCards(final String fam, final Map<String, ArrayList<String>> famItems ){
        //mDatabase.child(CATEGORY_NAME).child(itemName);
        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();
        final CollectionReference colRef = mFirestore.collection("Items"); //collection of items from database
        //DocumentReference docRef = mFirestore.collection("glass").document("glass");

            colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //Thread.sleep(1000);
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("nice", document.getId() + " => " + document.getData());
                                for(String currentID : famItems.get(fam)){
                                    if(currentID.equals(document.get("material_id"))){
                                        String name = (String) document.get("description");
                                        String descript = (String) document.get("long_description");
                                        String picUrl = (String) document.get("image");
                                        try {
                                            //InputStream is = (InputStream) new URL(picUrl).getContent();
                                            //Drawable itemPic = Drawable.createFromStream(is, null);
                                            Drawable itemPic = getDrawable(R.drawable.defaultimage);
                                            Card item = new Card(itemPic, name, descript);
                                            itemsInFam.add(item);
                                        } catch (Exception e) {
                                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }

                        } else {
                            Log.d("notNice", "Error getting documents: ", task.getException());
                        }
                    }
                });
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
        itemsInFam = new ArrayList<Card>();

        /*ArrayList<Card> plastic = new ArrayList<>();
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
            case ("Metal"):
                mAdapter = new ItemListAdapterWTR(this, metal, cat);
                break;
            case ("Paper"):
                mAdapter = new ItemListAdapterWTR(this, paper, cat);
                break;
            default:
                mAdapter = new ItemListAdapterWTR(this, plastic, cat);
                break;
        }

         */
        String cat = getIntent().getStringExtra(CATEGORY_NAME);

        Resources res = getApplicationContext().getResources();
        String key = res.getString(R.string.earth911);
        try{
            Map<String, ArrayList<String>> dum = getDatabase(key);
            makeCards(cat, dum);
            ItemListAdapterWTR mAdapter = new ItemListAdapterWTR(this, itemsInFam, cat);
            recycleView.setAdapter(mAdapter);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }


    }
    /*
    public void sendToItemPage(View view){
        Intent itemIntent = new Intent(this, ItemPage.class);
        startActivity(itemIntent);
    }*/

}
