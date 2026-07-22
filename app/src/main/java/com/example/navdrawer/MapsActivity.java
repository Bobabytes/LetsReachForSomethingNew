package com.example.navdrawer;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.navdrawer.databinding.ActivityMapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fabBackToFood = findViewById(R.id.fabBackToFood);
        fabBackToFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MapsActivity.this, MainActivity.class);
                in.putExtra("WORD_KEY", "Map Section");
                startActivity(in);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        */
        // Retrieve the Bundle from the Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ArrayList<String> selectedFoods = bundle.getStringArrayList("selectedFoods");

            ArrayList<LatLng> selectedLocations = new ArrayList<>();
            if (selectedFoods != null) {
                if (selectedFoods.contains("Pizza")) {
                    LatLng pizzaLocation = new LatLng(40.8178966653078, 14.175738011624004); // Example location
                    mMap.addMarker(new MarkerOptions().position(pizzaLocation).title("Pizza Place"));
                    selectedLocations.add(pizzaLocation);
                }
                if (selectedFoods.contains("Burgers")) {
                    LatLng burgerLocation = new LatLng(51.52380951603619, -0.07295501534566659); // Example location
                    mMap.addMarker(new MarkerOptions().position(burgerLocation).title("Burger Place"));
                    selectedLocations.add(burgerLocation);
                }
                if (selectedFoods.contains("Pasta")) {
                    LatLng pastaLocation = new LatLng(45.48489176849681, 12.416227657672833); // Example location
                    mMap.addMarker(new MarkerOptions().position(pastaLocation).title("Pasta Place"));
                    selectedLocations.add(pastaLocation);
                }
            }

            // Calculate the average location and move the camera
            if (!selectedLocations.isEmpty()) {
                double avgLat = 0;
                double avgLng = 0;

                for (LatLng location : selectedLocations) {
                    avgLat += location.latitude;
                    avgLng += location.longitude;
                }

                avgLat /= selectedLocations.size();
                avgLng /= selectedLocations.size();

                LatLng averageLocation = new LatLng(avgLat, avgLng);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(averageLocation, 4));
            }
        }
    }
}