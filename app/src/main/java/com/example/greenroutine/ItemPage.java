package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class ItemPage extends AppCompatActivity implements OnMapReadyCallback {

    private static final String ITEM_NAME = "ITEM_NAME";
    private static final String PICTURE_PATH = "PICTURE_PATH";

    private FusedLocationProviderClient fusedLocationClient;
    public String itemName;
    private String pictureFilePath;
    private double lat = 30.2886486;
    private double lng = -97.7376337;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_item_page);

        itemName = getIntent().getStringExtra(ITEM_NAME);
        pictureFilePath = getIntent().getStringExtra(PICTURE_PATH);

        setName(itemName);
        setPicture(pictureFilePath);

        //itemName = getIntent().getStringExtra(ITEM_NAME);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });


        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void setName(String itemName){
        TextView totalTextView = findViewById(R.id.name);
        totalTextView.setText(itemName);
    }

    private void setPicture(String pictureFilePath){
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(parent.getResources().getIdentifier());
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
}
