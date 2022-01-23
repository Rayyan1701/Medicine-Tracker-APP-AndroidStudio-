package com.example.android.project;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetNearbyPlaces extends AsyncTask<Object,String,String>
{

    private String googleplaceData;
    private GoogleMap mMap;
    private String url;


    @Override
    protected String doInBackground(Object... objects) {

        mMap=(GoogleMap)objects[0];
        url=(String)objects[1];

        DownloadUrl downloadUrl =new DownloadUrl();
        try {
            googleplaceData=downloadUrl.ReadTheURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return googleplaceData;
    }

    @Override
    protected void onPostExecute(String s) {
        List<HashMap<String,String>> nearbyPlaceList = null;
        DataParser dataParser=new DataParser();
        nearbyPlaceList=dataParser.parse(s);
        DisplayNearbyPlaces(nearbyPlaceList);

       // Toast.makeText(searchMedicalshopActivity,"hello there ...",Toast.LENGTH_LONG).show();
    }

    private void DisplayNearbyPlaces(List<HashMap<String,String>> nearbyPlaceList)
    {
        Log.d("getnearbuplaces","i=00000000000000001"+"   "+nearbyPlaceList.size());
        for(int i=0; i<nearbyPlaceList.size();i++)
        {
            Log.d("getnearbuplaces","i="+i);

            MarkerOptions markeroptions=new MarkerOptions();

            HashMap<String ,String > googleNearByplace= nearbyPlaceList.get(i);
            String nameofPlace;
            nameofPlace = googleNearByplace.get("place_name");
            String vicinity = googleNearByplace.get("vicinity");
            double lat = Double.parseDouble(googleNearByplace.get("lat"));
            double lng = Double.parseDouble(googleNearByplace.get("lng"));


            LatLng latLng = new LatLng(lat,lng);
            markeroptions.position(latLng);
            markeroptions.title(nameofPlace+" : "+vicinity);
            markeroptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(markeroptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));


        }
    }


}
