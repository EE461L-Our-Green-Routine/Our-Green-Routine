package com.example.greenroutine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MaterialsParser {

    /* Example JSON Material entry
    result: [{"description": "#1 Plastic Bags",
     "url": "",
     "description_legacy": "",
     "material_id": 445,
     "long_description": "Plastic bags are used to transport products
     or to seal foods. #1 Plastic bags may be difficult to recycle
     because they have limited markets.",
     "family_ids": [9, 106, 108],
     "image": "materials/1-plastic-bags.jpg"}
     {...}...]
    */

    private FirebaseFirestore mFirestore;

    public void parse(){
        ArrayList<materialEntry> matList = new ArrayList<materialEntry>();
        String description;
        String url;
        String description_legacy;
        String material_id;
        String long_description;
        String[] family_ids = new String[3];
        String image;

        //matString needs to get materialsResponse.txt in res/values
        String matString = "";

        try {
            //create json object from string
            JSONObject locObj = new JSONObject(matString);
            //create array of "result"
            JSONArray locArray = locObj.getJSONArray("result");

            for(int i = 0; i < matString.length(); i++){
                description = locArray.getJSONObject(i).getString("description");
                url = locArray.getJSONObject(i).getString("url");
                description_legacy = locArray.getJSONObject(i).getString("description_legacy");
                material_id = locArray.getJSONObject(i).getString("material_id");
                long_description = locArray.getJSONObject(i).getString("description");
                //need to get this working
                //family ids is an array
                //family_ids[0] = locArray.getJSONArray("family_ids").getJSONObject(0).getString("post_id");
                //family_ids[1] = locArray.getJSONObject(i).getString("description");
                //family_ids[2] = locArray.getJSONObject(i).getString("description");
                image = locArray.getJSONObject(i).getString("image");

                matList.add( new materialEntry(description, url, description_legacy, material_id, long_description, family_ids, image));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



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

    class materialEntry{
        public String description;
        public String url;
        public String description_legacy;
        public String material_id;
        public String long_description;
        public String[] family_ids;
        public String image;

        public materialEntry(String desc, String url, String desc_leg, String mat_id, String long_desc, String[] fam_id, String img){
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


