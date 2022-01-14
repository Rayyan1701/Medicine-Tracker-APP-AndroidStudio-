package com.example.android.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
//import android.location.LocationRequest;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

import com.example.android.project.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.Location;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

public class searchMedicalshopActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    View c1, c2, c3;

    private GoogleMap mMap;

    private GoogleApiClient googleApiClient;

    private LocationRequest locationRequest;

    private Location lastlocation;
    private Marker curruserloc;
    private static  final int Request_User_Location_Code=0;


    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medicalshop);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


        //loadFragment(new MapFragment());


        searchView = findViewById(R.id.idSearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();

                // below line is to create a list of address
                // where we will store the list of all address.
                List<Address> addressList = null;

                // checking if the entered location is null or not.
                if (location != null || !location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(searchMedicalshopActivity.this);
                    try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // on below line we are getting the location
                    // from our list a first position.
                    Address address;
                    try {
                        address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                        // on below line we are adding marker to that position.
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));

                        // below line is to animate camera to that position.
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    } catch (Exception e) {
                        Toast.makeText(searchMedicalshopActivity.this, "no location as such!", Toast.LENGTH_LONG).show();
                    }


                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        c1 = (View) findViewById(R.id.today_button);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<TodayInfoActivity> temp = TodayInfoActivity.class;
                myfunc(temp);
            }
        });

        c2 = (View) findViewById(R.id.check_prog_btn);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<checkScheduleActivity> temp = checkScheduleActivity.class;
                myfunc(temp);
            }
        });

        c3 = (View) findViewById(R.id.add_med_btn);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<addMedicineActivity> temp = addMedicineActivity.class;
                myfunc(temp);
            }
        });


        BottomNavigationView bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        View v = bottomNavigation.findViewById(R.id.med_map_btn);
        v.performClick();


        mapFragment.getMapAsync(this);

    }

    public void myfunc(Class temp) {
        Intent intent = new Intent(this, temp);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.mymapLinearlayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Add a marker in Sydney and move the camera
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            buildgoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }

    }

    public boolean checkUserLocationPermission()
    {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},Request_User_Location_Code);
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient == null) {
                            buildgoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);

                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
                }
        }


    }

    protected  synchronized  void  buildgoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        lastlocation= location;
        if(curruserloc != null)
        {
            curruserloc.remove();
        }
        LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title("User Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        curruserloc=mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if(googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest= new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,this);

        }



    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}