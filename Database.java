package com.mpontikes.database;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Database {
    static Firestore database;
    public static void main(String[] args) throws Exception {
        FileInputStream serviceAccount = new FileInputStream("key.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://ee461l-our-green-1570943687356.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
        database = FirestoreClient.getFirestore();
        ArrayList<materialEntry> data = getDatabase("error");
        populateDatabase(data, "testmp");
    }

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
    public static void populateDatabase(ArrayList<materialEntry> matList, String group) throws ExecutionException, InterruptedException {
        for(materialEntry m : matList){
            Map<String, Object> currentItem = new HashMap<>();
            currentItem.put("description", m.description);
            currentItem.put("url", m.url);
            currentItem.put("description_legacy", m.description_legacy);
            currentItem.put("material_id", m.material_id);
            currentItem.put("long_description", m.long_description);
            currentItem.put("image", m.image);
            currentItem.put("family_ids", m.family_ids );
            String key = m.description.replaceAll("/", "-");
            ApiFuture<WriteResult> future = database.collection(group).document(key).set(currentItem);
            System.out.println("Update time : " + future.get().getUpdateTime());
        }
    }
    static class materialEntry {
        public String description;
        public String url;
        public String description_legacy;
        public String material_id;
        public String long_description;
        public ArrayList<String> family_ids;
        public String image;

        public materialEntry(String desc, String url, String desc_leg, String mat_id, String long_desc, JSONArray fam_id, String img) {
            this.description = desc;
            this.url = url;
            this.description_legacy = desc_leg;
            this.material_id = mat_id;
            this.long_description = long_desc;

            family_ids = new ArrayList<>();
            try {
                for (int i = 0; i < fam_id.length(); i++) {
                    family_ids.add(fam_id.get(i).toString());
                }
            }
            catch(JSONException e){
                System.out.println("Problem with converting arrays");
            }

            this.image = img;
        }

    }

}
