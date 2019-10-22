package com.example.greenroutine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class ItemPage extends AppCompatActivity implements OnMapReadyCallback {

    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String PICTURE_PATH = "PICTURE_PATH";
    private static final String CATEGORY_NAME = "CATEGORY_NAME";
    private static final int FINE_LOCATION_REQUEST = 69;
    private final Object initLock = new Object();
    private FirebaseFirestore mFirestore;
    private FusedLocationProviderClient fusedLocationClient;
    public String itemName;
    public String categoryName;
    private String pictureFilePath;
    private String cat;
    private double lat;
    private double lng;

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
        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_item_page);

        setupItemDetails();

        checkPermissions();

        //getCurrentLocation();

        //displayMap();

        // [START get_firestore_instance]
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // [END get_firestore_instance]

        // [START set_firestore_settings]
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        // [END set_firestore_settings]
    }

    private void setupItemDetails(){
        itemName = getIntent().getStringExtra(ITEM_NAME);
        categoryName = getIntent().getStringExtra(CATEGORY_NAME);

        pictureFilePath = getIntent().getStringExtra(PICTURE_PATH);
        cat = getIntent().getStringExtra(CATEGORY_NAME);
        Toast.makeText(getApplicationContext(),categoryName,Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),itemName,Toast.LENGTH_SHORT).show();
        //setLink();
        setName(itemName);
        setPicture(pictureFilePath);
    }

    private void setLink(){
        //mDatabase.child(CATEGORY_NAME).child(itemName);
//        final String[] link = new String[1];
//        DocumentReference docRef = mFirestore.collection(cat.toLowerCase()).document(itemName.toLowerCase());
//        //
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    link[0] = (String) document.get("recycling_guide");
//
//            }
//        }});
//        //
        String[] link = new String[1];
        link[0] = "a";
        TextView linkText = findViewById(R.id.link);
        linkText.setText(link[0]);
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
}
