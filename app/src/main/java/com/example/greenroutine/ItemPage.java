package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import org.json.*;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemPage extends AppCompatActivity implements OnMapReadyCallback {

    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String PICTURE_PATH = "PICTURE_PATH";
    private static final int FINE_LOCATION_REQUEST = 69;
    private final Object initLock = new Object();

    private FusedLocationProviderClient fusedLocationClient;
    public String itemName;
    private String pictureFilePath;
    private double lat;
    private double lng;


    public ArrayList<String> locNames;
    public ArrayList<String> locDist;
    public ArrayList<String> locLat;
    public ArrayList<String> locLng;

    public String parser(String name){
        char parsed[] = name.toCharArray();
        int spaces=0;
        for(int i=0; i<parsed.length; i++){
            if(parsed[i]==' '&& i<parsed.length-1){
                spaces++;
                int temp=i;
                for(int j=i+1; j<parsed.length; j++){
                    parsed[temp]=parsed[j];
                    temp++;
                }
            }
            if((int)parsed[i]>64&&(int)parsed[i]<90){
                parsed[i]=(char)((int)parsed[i]+32);
            }
        }
        char shortened[]=new char[parsed.length-spaces];
        for(int i=0; i<parsed.length-spaces; i++){
            shortened[i]=parsed[i];
        }
        String str = new String(shortened);
        return str;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_item_page);

        setupItemDetails();

        checkPermissions();

        locNames = new ArrayList<String>();
        locDist = new ArrayList<String>();


        //getCurrentLocation();

        //displayMap();
    }

    private void setupItemDetails(){
        itemName = getIntent().getStringExtra(ITEM_NAME);
        pictureFilePath = getIntent().getStringExtra(PICTURE_PATH);

        setName(itemName);
        setPicture(pictureFilePath);
    }


    private void setName(String itemName){
        TextView totalTextView = findViewById(R.id.name);
        totalTextView.setText(itemName);
    }

    private void setPicture(String pictureName){
        ImageView imageView = findViewById(R.id.imageView);
        String imageName = parser(itemName);
        imageView.setImageResource(getResources().getIdentifier(imageName, "drawable", this.getPackageName()));
    }


    @Override
    public void onMapReady(GoogleMap map) {

        LatLng location = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions().position(location)
                .title("You are here."));
        map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));

        dropLocationPins(map);
//    map.setMyLocationEnabled(true);
//        fusedLocationClient.getLastLocation();
//        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(,
//                        -73.98180484771729));
//        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
    }

    private void checkPermissions(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        FINE_LOCATION_REQUEST);

            }
        } else {
            // Permission has already been granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case FINE_LOCATION_REQUEST : {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
//                    Intent intent = getIntent();
//                    finish();
//                    startActivity(intent);
                    getCurrentLocation();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void getCurrentLocation(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {


                        //lat = 30.2886486;
                        //lng = -97.7376337;

                        Context context = getApplicationContext();
                        if (location != null) {
                            // Logic to handle location object
                            //lat = 30.2886486;
                            //lng = -97.7376337;
                            //lat = 0;
                            //lng = 0;
                            // Got last known location. In some rare situations this can be null.
                            Toast debugToast = Toast.makeText(context, lat+" "+lng, Toast.LENGTH_LONG);

                            lat = location.getLatitude();
                            lng = location.getLongitude();
                            displayMap();
                            nearbyRecycling();
                            setRecyclingLocations();
                        }
                        else {
                            Toast nullToast = Toast.makeText(context, "Location was not available", Toast.LENGTH_LONG);
                            nullToast.show();
                        }
                    }
                });
    }

    private void displayMap(){
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



/* Example JSON Location entry
    result: [{"curbside": false,
    "description": "Sprint Store", 
    "distance": 0.5, 
    "longitude": -97.74190159539837, 
    "latitude": 30.28700741685142, 
    "location_type_id": 28, 
    "location_id": "Q1RQNVJeW1tCUQ", 
    "municipal": false}
    {...}...]

*/
    //https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl



    private void nearbyRecycling(){



        RequestQueue queue = Volley.newRequestQueue(this);
        //api request, there are additonal parameters such as item wish to recycle fyi
        String url ="https://api.earth911.com/earth911.searchLocations?api_key="+ getString(R.string.earth911)
                + "&latitude=" + lat + "&longitude=" + lng;

        try {
            JSONObject db = getData(url);

            JSONArray locArray = db.getJSONArray("result");

            //only store the 5 closest, as the api response is sorted by distance
            for (int i = 0; i < 5; i++) {
                //add description / name of result i
                locNames.add(locArray.getJSONObject(i).getString("description"));
                //add distance of result i
                locDist.add(locArray.getJSONObject(i).getString("distance"));
                //add lat of result i
                locLat.add(locArray.getJSONObject(i).getString("latitude"));
                //add long of result i
                locLng.add(locArray.getJSONObject(i).getString("longitude"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //code reuse from MaterialsParser
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

    private void setRecyclingLocations() {

        //setting location names

        ((TextView)findViewById(R.id.location1)).setText(locNames.get(0));
        ((TextView)findViewById(R.id.location2)).setText(locNames.get(1));
        ((TextView)findViewById(R.id.location3)).setText(locNames.get(2));
        ((TextView)findViewById(R.id.location4)).setText(locNames.get(3));
        ((TextView)findViewById(R.id.location5)).setText(locNames.get(4));


        //setting the distance fields
        ((TextView)findViewById(R.id.distance1)).setText(locDist.get(0));
        ((TextView)findViewById(R.id.distance2)).setText(locDist.get(1));
        ((TextView)findViewById(R.id.distance3)).setText(locDist.get(2));
        ((TextView)findViewById(R.id.distance4)).setText(locDist.get(3));
        ((TextView)findViewById(R.id.distance5)).setText(locDist.get(4));

    }


    private void dropLocationPins(GoogleMap googleMap) {
        LatLng location0 = new LatLng (Double.parseDouble(locLat.get(0)), Double.parseDouble(locLng.get(0)));
        LatLng location1 = new LatLng (Double.parseDouble(locLat.get(1)), Double.parseDouble(locLng.get(1)));
        LatLng location2 = new LatLng (Double.parseDouble(locLat.get(2)), Double.parseDouble(locLng.get(2)));
        LatLng location3 = new LatLng (Double.parseDouble(locLat.get(3)), Double.parseDouble(locLng.get(3)));
        LatLng location4 = new LatLng (Double.parseDouble(locLat.get(4)), Double.parseDouble(locLng.get(4)));

        LatLng[] locationPins = new LatLng [] {location0, location1, location2, location3, location4};

        String location0_name = locNames.get(0);
        String location1_name = locNames.get(1);
        String location2_name = locNames.get(2);
        String location3_name = locNames.get(3);
        String location4_name = locNames.get(4);

        String[] locationNames = new String [] {location0_name, location1_name, location2_name, location3_name, location4_name};

        for (int i = 0; i < locationPins.length; i++){
            googleMap.addMarker(new MarkerOptions().position(locationPins[i])
                    .title(locationNames[i]));
        }

    }

}
