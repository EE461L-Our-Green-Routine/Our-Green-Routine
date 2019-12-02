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
import java.sql.Array;
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
    private ItemListWTR whatever = this;
    private boolean cardsMade = false;
    private String cat;

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
    private void makeCards(final ArrayList<String> famItems ){
        //mDatabase.child(CATEGORY_NAME).child(itemName);
        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();
        final CollectionReference colRef = mFirestore.collection("testmp"); //collection of items from database
        //DocumentReference docRef = mFirestore.collection("glass").document("glass");

            colRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //Thread.sleep(1000);
                        Map matIDs = new HashMap();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("nice", document.getId() + " => " + document.getData());
                                for(String currentID : famItems){
                                    Object docMatId = document.getData().get("material_id");
                                    if(currentID.equals(docMatId)){
                                        String name = (String) document.get("description");
                                        String descript = (String) document.get("long_description");
                                        String picUrl = (String) document.get("image");
                                        try {
                                            //InputStream is = (InputStream) new URL(picUrl).getContent();
                                            //Drawable itemPic = Drawable.createFromStream(is, null);
                                            Drawable itemPic = getDrawable(R.drawable.defaultimage);
                                            Card item = new Card(itemPic, name, descript);
                                            itemsInFam.add(item);
                                            matIDs.put(name, currentID);
                                        } catch (Exception e) {
                                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }


                        } else {
                            Log.d("notNice", "Error getting documents: ", task.getException());
                        }
                        cardsMade = true;
                        ItemListAdapterWTR mAdapter = new ItemListAdapterWTR(whatever, itemsInFam, matIDs);
                        recycleView.setAdapter(mAdapter);
                    }

                });

    }


    public class cardTask extends AsyncTask<ArrayList<String>, Integer, Void> {


        @Override
        protected Void doInBackground(ArrayList<String>... arrayLists) {

            makeCards(arrayLists[0]);
            return null;
        }


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


        cat = getIntent().getStringExtra(CATEGORY_NAME);
        ((TextView)findViewById(R.id.textView4)).setText(cat+":");
        ArrayList<String> catArray = new ArrayList<>();
        catArray.add(cat);
        Resources res = getApplicationContext().getResources();
        String key = res.getString(R.string.earth911);
        try{
            Map<String, ArrayList<String>> dum = getDatabase(key);
            ArrayList<String> itemList = dum.get(cat);
            new cardTask().execute(itemList);



        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }


    }

}
