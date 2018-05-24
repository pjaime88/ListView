package com.tutorials.hp.listview_crud;


import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Copyright © 2018 Paulo Jaime,
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Purpose: An app to demonstrate a ListView with a SimpleAdapter
 * The simple adapter is not so simple, but it does allow a list with multiple row components
 * to be constructed from a pre-defined adapter. Both activities of this example create and
 * manipulate a listView using a simple adapter
 *
 * @author Paulo Jaime Paulo.Jaime@asu.edu
 *
 * @version February 9, 2018
 */

public class PlaceLibrary {

    public ArrayList<PlaceDescription> places = new ArrayList<>();
    public ArrayList<String> spinnerDataName = new ArrayList<>();
    public ArrayList<String> spinnerDatalat = new ArrayList<>();
    public ArrayList<String> spinnerDatalong = new ArrayList<>();

    public void save(PlaceDescription name)
    {
        places.add(name);
    }

    public ArrayList<String> getNames()
    {

        ArrayList<String> names = new ArrayList<>();
        for (PlaceDescription place: places
             ) {
            names.add(place.name);
        }

        return names;
    }

    public ArrayList<String> getDescrip()
    {
        ArrayList<String> descriptions = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            descriptions.add(place.Description);
        }

        return descriptions;
    }

    public ArrayList<String> getCategory()
    {
        ArrayList<String> categories = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            categories.add(place.Category);
        }

        return categories;
    }

    public ArrayList<String> getElevation()
    {
        ArrayList<String> elevation = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            elevation.add(place.Elevation);
        }

        return elevation;
    }

    public ArrayList<String> getLongitude()
    {
        ArrayList<String> longitude = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            longitude.add(place.Longitude);
        }
        return longitude;
    }

    public ArrayList<String> getLatitude()
    {
        ArrayList<String> latitude = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            latitude.add(place.Latitude);
        }
        return latitude;
    }


    public ArrayList<String> getAddressTitle()
    {
        ArrayList<String> addresstitle = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            addresstitle.add(place.AddressTitle);
        }
        return addresstitle;
    }

    public ArrayList<String> getAddressStreet()
    {
        ArrayList<String> addressstreet = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            addressstreet.add(place.AddresStreet);
        }
        return addressstreet;
    }

    public ArrayList<String> getImage()
    {
        ArrayList<String> image = new ArrayList<>();
        for (PlaceDescription place: places
                ) {
            image.add(place.Image);
        }
        return image;
    }


    public Boolean update(int position,PlaceDescription newPlace)
    {
       try {
           places.remove(position);
           places.add(position,newPlace);

           return true;
       }catch (Exception e)
       {
           e.printStackTrace();
          return false;
        }
    }

    public Boolean delete(int position)
    {
        try {
            places.remove(position);
            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }

    public void loadJSONFromAsset(Activity parent) {
        String json = null;
        try {
            InputStream is = parent.getApplicationContext().getResources().openRawResource(R.raw.places);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer sb = new StringBuffer();

            while(br.ready()){
                sb.append(br.readLine());
            }
            json = new String(sb);
            //json = new String(sb, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            Log.d("Tag", json);
            JSONObject obj = new JSONObject(json);
            JSONArray p = obj.getJSONArray("places");
            for (int i = 0; i < p.length(); i++) {
                JSONObject jsonObject = p.getJSONObject(i);
                PlaceDescription newObj = new PlaceDescription();
                newObj.name = jsonObject.getString("name");
                spinnerDataName.add(newObj.name);
                newObj.Description = jsonObject.getString("description");
                newObj.AddressTitle = jsonObject.getString("address-title");
                newObj.AddresStreet = jsonObject.getString("address-street");
                newObj.Elevation = jsonObject.getString("elevation");
                newObj.Latitude = jsonObject.getString("latitude");
                newObj.Longitude = jsonObject.getString("longitude");
                newObj.Category = jsonObject.getString("category");

                this.places.add(newObj);
            }

        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
    }
}
