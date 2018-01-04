package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.khalessi.gretas_vokabeltrainer.database_vocabulary.UnitDatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.database_vocabulary.Units;

import java.util.ArrayList;

public class UnitActivity extends AppCompatActivity {

    UnitCustomAdapter myCustomAdapter = null;
    ListView listView = null;
    UnitDatabaseHelper db = null;
    ArrayList<Units> cars = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new UnitDatabaseHelper(this);
        db.recreateDatabase();
        db.insertSomeUnits();
        cars = db.getData();
        myCustomAdapter = new UnitCustomAdapter(this, R.layout.unit_details, cars);

        listView = (ListView) findViewById(R.id.lv_unitListView);
        listView.setAdapter(myCustomAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intentAddUnit = new Intent(getApplicationContext(), UnitAddActivity.class);
                startActivity(intentAddUnit);
            }
        });
    }

}
