package com.example.greenroutine;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class MaterialsParser {
    private FirebaseFirestore mFirestore;
    public static ArrayList<materialEntry> getDatabase(String key) throws IOException, JSONException {
        ArrayList<materialEntry> out = new ArrayList();
        JSONArray data = (JSONArray)(getData("https://api.earth911.com/earth911.getMaterials?api_key=" + key).get("result"));
        for(int i = 0; i < data.length(); i++){
            JSONObject item = (JSONObject)data.get(i);
            if(!item.has("family_ids")){
                JSONArray non = new JSONArray();
                non.put(-1);
                item.put("family_ids",non);
            }
            materialEntry add = new materialEntry((String)item.get("description"), (String)item.get("url"), (String)item.get("description_legacy"), String.valueOf(item.get("material_id")), (String)item.get("long_description"), (JSONArray) (item.get("family_ids")), (String)item.get("image"));
            out.add(add);
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
    public void firestoreInit(){
//        FirebaseApp.initializeApp();
        mFirestore = FirebaseFirestore.getInstance();
    }

    public void populateDatabase(ArrayList<materialEntry> matList){
        for(materialEntry m : matList){
            Map<String, Object> currentItem = new HashMap<>();
            currentItem.put("description", m.description);
            currentItem.put("url", m.url);
            currentItem.put("description_legacy", m.description_legacy);
            currentItem.put("material_id", m.material_id);
            currentItem.put("long_description", m.long_description);
            currentItem.put("image", m.image);
            currentItem.put("family_ids", m.family_ids );
            mFirestore.collection("ItemList")
                    .add(currentItem)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override

                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("nice" , "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("notNice", "error adding document", e);
                        }
                    });
        }
    }
    static class materialEntry {
        public String description;
        public String url;
        public String description_legacy;
        public String material_id;
        public String long_description;
        public JSONArray family_ids;
        public String image;

        public materialEntry(String desc, String url, String desc_leg, String mat_id, String long_desc, JSONArray fam_id, String img) {
            this.description = desc;
            this.url = url;
            this.description_legacy = desc_leg;
            this.material_id = mat_id;
            this.long_description = long_desc;
            this.family_ids = fam_id;
            this.image = img;
        }

    }
}