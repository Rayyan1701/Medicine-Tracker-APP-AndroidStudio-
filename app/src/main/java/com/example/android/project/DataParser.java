package com.example.android.project;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser
{
    private HashMap<String,String> getSingleNearbyPlace(JSONObject googlePlaceJSON)
    {
        HashMap<String,String> googlePlaceMap = new HashMap<>();
        String NameOfPlace = "-NA-";
        String vicinity  = "-NA-";
        String latitude ="";
        String longitude ="";
        String reference ="";

        try
        {
            if(!googlePlaceJSON.isNull("name"))
            {
                NameOfPlace=googlePlaceJSON.getString("name");
            }
            if(!googlePlaceJSON.isNull("vicinity"))
            {
                vicinity=googlePlaceJSON.getString("vicinity");
            }
            latitude=googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude=googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");

            reference=googlePlaceJSON.getString("reference");

            googlePlaceMap.put("place_name",NameOfPlace);
            googlePlaceMap.put("vicinity",vicinity);
            googlePlaceMap.put("lat",latitude);
            googlePlaceMap.put("lng",longitude);
            googlePlaceMap.put("reference",reference);



        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return googlePlaceMap;

    }


    private List<HashMap<String,String>> getAllNearbyPlaces(JSONArray jsonarray)
    {
        int counter = jsonarray.length();

        List<HashMap<String,String>> NearbypacesList=new ArrayList<>();

        HashMap<String,String> nearbypalcesmap=null;

        Log.d("getnearbuplaces","i=1111110"+"   "+counter);
        for(int i=0;i<counter;i++)
        {
            try {
                nearbypalcesmap=getSingleNearbyPlace((JSONObject)jsonarray.get(i));
                NearbypacesList.add(nearbypalcesmap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  NearbypacesList;
    }

    public List<HashMap<String,String>> parse(String JSONdata)
    {
        JSONArray jsonarray = null;
        JSONObject jsonobject;

        try
        {
            jsonobject=new JSONObject(JSONdata);
            jsonarray=jsonobject.getJSONArray("results");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return getAllNearbyPlaces(jsonarray);

    }


}
