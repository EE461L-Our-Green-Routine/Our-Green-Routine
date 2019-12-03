package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnSuccessListener;
import org.json.*;

import java.util.ArrayList;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import static java.lang.Integer.parseInt;

public class ItemPage extends AppCompatActivity implements OnMapReadyCallback {

    private static final String ITEM_NAME = "ITEM_NAME";
    private static final int FINE_LOCATION_REQUEST = 69;
    private String itemName;
    private String description;
    private double lat;
    private double lng;
    private GoogleMap map;


    private ArrayList<String> locNames;
    private ArrayList<String> locDist;
    private ArrayList<Double> locLat;
    private ArrayList<Double> locLng;
    private int limit=0;

    private Command earth911Com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_item_page);
        locNames = new ArrayList<>();
        locDist = new ArrayList<>();
        locLat = new ArrayList<>();
        locLng = new ArrayList<>();
        setupItemDetails();
        checkPermissions();
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
        //categoryName = getIntent().getStringExtra(CATEGORY_NAME);
        String DESCRIPTION = "DESCRIPTION";
        description = getIntent().getStringExtra(DESCRIPTION);
        setName(itemName);
        setDescrip(description);
    }

    private void setName(String itemName){
        TextView totalTextView = findViewById(R.id.name);
        totalTextView.setText(itemName);
    }

    private void setDescrip(String descrip){
        TextView totalTextView = findViewById(R.id.description);
        totalTextView.setText(descrip);
    }
    @Override
    public void onMapReady(GoogleMap map) {
        LatLng location = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions().position(location).title("You are here."));
        map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
        this.map = map;
        nearbyRecycling();
    }

    private void checkPermissions(){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if(requestCode == FINE_LOCATION_REQUEST) {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    getCurrentLocation();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
        }
            // other 'case' lines to check for other
            // permissions this app might request
    }

    private void getCurrentLocation(){
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Context context = getApplicationContext();
                        if (location != null) {
                            // Logic to handle location object
                            // Got last known location. In some rare situations this can be null.
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
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


    public void nearbyRecycling(){
        //api request, there are additonal parameters such as item wish to recycle fyi
        //array list is the list of parameters for the api request
        ArrayList<ArrayList<String>> params = new ArrayList<ArrayList<String>>();

        ArrayList<String> method = new ArrayList<String>();
        method.add("searchLocations");
        params.add(method);

        ArrayList<String> key = new ArrayList<String>();
        key.add("?api_key=");
        key.add(getString(R.string.earth911));
        params.add(key);

        ArrayList<String> latitude = new ArrayList<String>();
        latitude.add("&latitude=");
        latitude.add(Double.toString(lat));
        params.add(latitude);

        ArrayList<String> longitude = new ArrayList<String>();
        longitude.add("&longitude=");
        longitude.add(Double.toString(lng));
        params.add(longitude);

        String ID = "ID";
        String matID = getIntent().getStringExtra(ID);
        int matIDNum = parseInt(matID);
        ArrayList<String> materialID = new ArrayList<String>();
        materialID.add("&material_id=");
        materialID.add(Integer.toString(matIDNum));
        params.add(materialID);

        ArrayList<String> maxRes = new ArrayList<String>();
        maxRes.add("&max_results=");
        maxRes.add("5");
        params.add(maxRes);

        earth911Command locCom = new earth911Command(params);
        setCommand(locCom);
        APIrequest();
        ArrayList<Object> parseArray = locCom.commandRequest();

        locNames = (ArrayList<String>)parseArray.get(0);
        locDist = (ArrayList<String>)parseArray.get(1);
        locLat = (ArrayList<Double>)parseArray.get(2);
        locLng = (ArrayList<Double>)parseArray.get(3);

        limit = locNames.size();

        dropLocationPins();
        setRecyclingLocations();
    }

    private void setCommand(Command command){
        this.earth911Com = command;
    }
    private void APIrequest(){
        earth911Com.execute();
    }

    private void setRecyclingLocations() {
        for (Integer i = 1; i < 6; i++){

            String name = "N/A";
            String dist = "N/A";
            if(limit>=i){
                name = locNames.get((i-1));
                dist = locDist.get((i-1)) + "mi";
            }
            int nameid = getResources().getIdentifier("location" + i.toString(), "id", this.getPackageName());
            ((TextView)findViewById(nameid)).setText(name);
            int distid = getResources().getIdentifier("distance" + i.toString(), "id", this.getPackageName());
            ((TextView)findViewById(distid)).setText(dist);

        }
    }


    private void dropLocationPins() {
        ArrayList<LatLng> locationPins = new ArrayList<>();
        ArrayList<String> locationNames = new ArrayList<>();
        for(Integer i = 0; i < 5; i++){
            LatLng location = null;
            String name = null;
            if(limit>i) {
                location = new LatLng(locLat.get(i), locLng.get(i));
                locationPins.add(location);
                name = locNames.get(i);
                locationNames.add(name);
            }
        }
        if(!locationPins.isEmpty() && !locationNames.isEmpty() ) {
            for (int i = 0; i < locationPins.size(); i++) {
                map.addMarker(new MarkerOptions().position(locationPins.get(i))
                        .title(locationNames.get(i)));
            }
        }
    }
}
