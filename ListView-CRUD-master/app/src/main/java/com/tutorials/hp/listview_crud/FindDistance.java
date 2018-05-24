package com.tutorials.hp.listview_crud;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
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

public class FindDistance extends Activity {

    private double lat1, lat2, lon1, lon2;
    private String unit;
    private String[] unitArray = {"K", "N"};
    private Button findDistanceBtn;
    private TextView la1, la2, lo1, lo2, result;
    private Spinner spinnerFrom, spinnerTo, spinnerUnit;
    private ArrayAdapter <String> adapter1;
    private ArrayAdapter <String> adapter2;
    private ArrayAdapter <String> adapter3;
    private PlaceLibrary obj;
    private String[] arrayspinnDataName = {"ASU-West", "UAK-Anchorage", "Toreros", "Barrow-Alaska", "Calgary-Alberta", "London-England", "Moscow-Russia", "New-York-NY", "Notre-Dame-Paris", "Circlestone", "Reavis-Ranch", "Rogers-Trailhead", "Reavis-Grave", "Muir-Woods", "Windcave-Peak"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        findDistanceBtn = (Button) findViewById(R.id.findDistanceBtn);
        la1 = (TextView) findViewById(R.id.lat1);
        la2 = (TextView) findViewById(R.id.lat2);
        lo1 = (TextView) findViewById(R.id.lon1);
        lo2 = (TextView) findViewById(R.id.lon2);
        result = (TextView) findViewById(R.id.result);

        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        spinnerUnit = (Spinner) findViewById(R.id.spinnerUnit);


        adapter1 = new ArrayAdapter<String>(FindDistance.this, android.R.layout.simple_list_item_1, unitArray);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter1);

        adapter2 = new ArrayAdapter<String>(FindDistance.this, android.R.layout.simple_list_item_1, arrayspinnDataName);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter2);
        spinnerTo.setAdapter(adapter2);

        obj = new PlaceLibrary();
        //arrayspinnDataName = obj.spinnerDataName.toArray(new String[obj.spinnerDataName.size()]);


        findDistanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spinnerFrom.getSelectedItem().toString()=="ASU-West"){lat1 = 33.608979 ; lon1 = -112.159469;};
                if(spinnerFrom.getSelectedItem().toString()=="UAK-Anchorage"){lat1 = 61.189748 ; lon1 = -149.826721;};
                if(spinnerFrom.getSelectedItem().toString()=="Toreros"){lat1 = 32.771923 ; lon1 = -117.188204;};
                if(spinnerFrom.getSelectedItem().toString()=="Barrow-Alaska"){lat1 = 71.287881 ; lon1 = -156.779417;};
                if(spinnerFrom.getSelectedItem().toString()=="Calgary-Alberta"){lat1 = 51.131377 ; lon1 = -114.011246;};
                if(spinnerFrom.getSelectedItem().toString()=="London-England"){lat1 = 51.481959 ; lon1 = -0.445286;};
                if(spinnerFrom.getSelectedItem().toString()=="Moscow-Russia"){lat1 = 55.758666 ; lon1 = 37.604058;};
                if(spinnerFrom.getSelectedItem().toString()=="New-York-NY"){lat1 = 40.712991 ; lon1 = -74.005948;};
                if(spinnerFrom.getSelectedItem().toString()=="Notre-Dame-Paris"){lat1 = 48.852972 ; lon1 = 2.349910;};
                if(spinnerFrom.getSelectedItem().toString()=="Circlestone"){lat1 = 33.477524 ; lon1 = -111.134345;};
                if(spinnerFrom.getSelectedItem().toString()=="Reavis-Ranch"){lat1 = 33.491154 ; lon1 = -111.155385;};
                if(spinnerFrom.getSelectedItem().toString()=="Rogers-Trailhead"){lat1 = 33.422212 ; lon1 = -111.173393;};
                if(spinnerFrom.getSelectedItem().toString()=="Reavis-Grave"){lat1 = 33.441499 ; lon1 = -111.182511;};
                if(spinnerFrom.getSelectedItem().toString()=="Muir-Woods"){lat1 = 37.8912 ; lon1 = -122.5957;};
                if(spinnerFrom.getSelectedItem().toString()=="Windcave-Peak"){lat1 = 33.476069 ; lon1 = -111.595709;};

                if(spinnerTo.getSelectedItem().toString()=="ASU-West"){lat2 = 33.608979 ; lon2 = -112.159469;};
                if(spinnerTo.getSelectedItem().toString()=="UAK-Anchorage"){lat2 = 61.189748 ; lon2 = -149.826721;};
                if(spinnerTo.getSelectedItem().toString()=="Toreros"){lat2 = 32.771923 ; lon2 = -117.188204;};
                if(spinnerTo.getSelectedItem().toString()=="Barrow-Alaska"){lat2 = 71.287881 ; lon2 = -156.779417;};
                if(spinnerTo.getSelectedItem().toString()=="Calgary-Alberta"){lat2 = 51.131377 ; lon2 = -114.011246;};
                if(spinnerTo.getSelectedItem().toString()=="London-England"){lat2 = 51.481959 ; lon2 = -0.445286;};
                if(spinnerTo.getSelectedItem().toString()=="Moscow-Russia"){lat2 = 55.758666 ; lon2 = 37.604058;};
                if(spinnerTo.getSelectedItem().toString()=="New-York-NY"){lat2 = 40.712991 ; lon2 = -74.005948;};
                if(spinnerTo.getSelectedItem().toString()=="Notre-Dame-Paris"){lat2 = 48.852972 ; lon2 = 2.349910;};
                if(spinnerTo.getSelectedItem().toString()=="Circlestone"){lat2 = 33.477524 ; lon2 = -111.134345;};
                if(spinnerTo.getSelectedItem().toString()=="Reavis-Ranch"){lat2 = 33.491154 ; lon2 = -111.155385;};
                if(spinnerTo.getSelectedItem().toString()=="Rogers-Trailhead"){lat2 = 33.422212 ; lon2 = -111.173393;};
                if(spinnerTo.getSelectedItem().toString()=="Reavis-Grave"){lat2 = 33.441499 ; lon2 = -111.182511;};
                if(spinnerTo.getSelectedItem().toString()=="Muir-Woods"){lat2 = 37.8912 ; lon2 = -122.5957;};
                if(spinnerTo.getSelectedItem().toString()=="Windcave-Peak"){lat2 = 33.476069 ; lon2 = -111.595709;};

                if(spinnerUnit.getSelectedItem().toString()=="N"){unit = "N";};
                if(spinnerUnit.getSelectedItem().toString()=="K"){unit = "K";};

                la1.setText(String.valueOf(lat1));
                la2.setText(String.valueOf(lat2));
                lo1.setText(String.valueOf(lon1));
                lo2.setText(String.valueOf(lon2));

                result.setText(String.valueOf(distance(lat1, lon1, lat2, lon2, unit)));

            }
        });


    }


    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
