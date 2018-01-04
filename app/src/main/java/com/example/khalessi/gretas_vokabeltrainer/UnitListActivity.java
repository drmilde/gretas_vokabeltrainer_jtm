package com.example.khalessi.gretas_vokabeltrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.khalessi.gretas_vokabeltrainer.database_vocabulary.Units;
import com.example.khalessi.gretas_vokabeltrainer.database_vocabulary.UnitDatabaseHelper;

import java.util.ArrayList;

public class UnitListActivity extends AppCompatActivity {

    // TODO diese klasse kann später weg, war nur zu Testzwecken drin

    UnitCustomAdapter myCustomAdapter = null;
    ListView listView = null;
    UnitDatabaseHelper db = null;
    ArrayList<Units> cars = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_list);


        db = new UnitDatabaseHelper(this);
        db.recreateDatabase();
        db.insertSomeUnits();
        cars = db.getData();
        myCustomAdapter = new UnitCustomAdapter(this, R.layout.unit_details, cars);

        listView = (ListView) findViewById(R.id.simpleListView);
        listView.setAdapter(myCustomAdapter);


    }
}
