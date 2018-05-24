package com.tutorials.hp.listview_crud;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    public ListView lv;
    public ArrayAdapter<String> adapter;
    public PlaceLibrary obj=new PlaceLibrary();
    public Dialog d;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////
        btn = (Button) findViewById(R.id.distanceBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(v);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        obj.loadJSONFromAsset(this);

        lv = (ListView) findViewById(R.id.lv);

        adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,obj.getNames());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayInputDialog(-1);
            }
        });

    }

    public void openActivity(View v){
        if(v.getId() == R.id.distanceBtn){
            Intent i = new Intent(this, FindDistance.class);
            startActivity(i);

        }

    }


    private void displayInputDialog(final int pos)
    {
        d=new Dialog(this);
        d.setTitle("LIST OF PLACES");
        d.setContentView(R.layout.input_dialog);

        final EditText nameEditTxt = (EditText) d.findViewById(R.id.txtName);
        final EditText descriptionEditTxt = (EditText) d.findViewById(R.id.txtDescription);
        final EditText categoryEditTxt = (EditText) d.findViewById(R.id.txtCategory);
        final EditText addresstitleEditTxt = (EditText) d.findViewById(R.id.txtAddressTitle);
        final EditText addressstreetEditTxt = (EditText) d.findViewById(R.id.txtAddressStreet);
        final EditText elevatioEditTxt = (EditText) d.findViewById(R.id.txtElevation);
        final EditText latitudeEditTxt = (EditText) d.findViewById(R.id.txtLatitude);
        final EditText longitudeEditTxt = (EditText) d.findViewById(R.id.txtLongitude);
        final EditText imageEditTxt = (EditText) d.findViewById(R.id.txtImage);

        Button addBtn= (Button) d.findViewById(R.id.addBtn);
        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);


        if(pos== -1)
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);

            nameEditTxt.setText(obj.getNames().get(pos));
            descriptionEditTxt.setText(obj.getDescrip().get(pos));
            categoryEditTxt.setText(obj.getCategory().get(pos));
            elevatioEditTxt.setText(obj.getElevation().get(pos));
            latitudeEditTxt.setText(obj.getLatitude().get(pos));
            longitudeEditTxt.setText(obj.getLongitude().get(pos));
            addressstreetEditTxt.setText(obj.getAddressStreet().get(pos));
            addresstitleEditTxt.setText(obj.getAddressTitle().get(pos));
            imageEditTxt.setText(obj.getImage().get(pos));
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                //VALIDATE
                if(nameEditTxt.getText().toString().length()>0 && nameEditTxt.getText().toString() != null)
                {
                    PlaceDescription pd = new PlaceDescription();
                    pd.name = nameEditTxt.getText().toString();
                    pd.Category = categoryEditTxt.getText().toString();
                    pd.Description = descriptionEditTxt.getText().toString();
                    pd.Elevation = elevatioEditTxt.getText().toString();
                    pd.AddressTitle = addresstitleEditTxt.getText().toString();
                    pd.AddresStreet = addressstreetEditTxt.getText().toString();
                    pd.Latitude = latitudeEditTxt.getText().toString();
                    pd.Longitude = longitudeEditTxt.getText().toString();
                    pd.Image = imageEditTxt.getText().toString();

                    //save
                    obj.save(pd);
                    nameEditTxt.setText("");
                    categoryEditTxt.setText("");
                    descriptionEditTxt.setText("");
                    addressstreetEditTxt.setText("");
                    addresstitleEditTxt.setText("");
                    latitudeEditTxt.setText("");
                    longitudeEditTxt.setText("");
                    imageEditTxt.setText("");
                    elevatioEditTxt.setText("");
                    adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,obj.getNames());
                    lv.setAdapter(adapter);

                }else
                {
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                PlaceDescription pd = new PlaceDescription();
                pd.name = nameEditTxt.getText().toString();
                pd.Category = categoryEditTxt.getText().toString();
                pd.Description = descriptionEditTxt.getText().toString();
                pd.Longitude = longitudeEditTxt.getText().toString();
                pd.Latitude = latitudeEditTxt.getText().toString();
                pd.AddresStreet = addressstreetEditTxt.getText().toString();
                pd.AddressTitle = addresstitleEditTxt.getText().toString();
                pd.Image = imageEditTxt.getText().toString();

                //VALIDATE
                if(nameEditTxt.getText().toString().length()>0 && nameEditTxt.getText().toString() != null)
                {
                    //save
                    if(obj.update(pos,pd))
                    {
                        nameEditTxt.setText(pd.name);
                        descriptionEditTxt.setText(pd.Description);
                        categoryEditTxt.setText(pd.Category);
                        addressstreetEditTxt.setText(pd.AddresStreet);
                        addresstitleEditTxt.setText(pd.AddressTitle);
                        elevatioEditTxt.setText(pd.Elevation);
                        latitudeEditTxt.setText(pd.Latitude);
                        longitudeEditTxt.setText(pd.Longitude);
                        imageEditTxt.setText(pd.Image);
                        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,obj.getNames());
                        lv.setAdapter(adapter);
                    }

                }else
                {
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DELETE
                if( obj.delete(pos))
                {
                    nameEditTxt.setText("");
                    categoryEditTxt.setText("");
                    descriptionEditTxt.setText("");
                    addressstreetEditTxt.setText("");
                    addresstitleEditTxt.setText("");
                    latitudeEditTxt.setText("");
                    longitudeEditTxt.setText("");
                    imageEditTxt.setText("");
                    elevatioEditTxt.setText("");
                    adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,obj.getNames());
                    lv.setAdapter(adapter);
                }
            }
        });

        d.show();
    }
}
