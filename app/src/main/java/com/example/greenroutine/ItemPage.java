package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
}
