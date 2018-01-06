package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.UnitDatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.ArrayList;

public class UnitActivity extends AppCompatActivity {

    UnitCustomAdapter unitCustomAdapter = null;
    ListView listView = null;
    UnitDatabaseHelper db = null;
    ArrayList<Unit> units = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new UnitDatabaseHelper(this);
        db.recreateDatabase();
        db.insertSomeUnits();
        units = db.getUnitsData();
        unitCustomAdapter = new UnitCustomAdapter(this, R.layout.unit_details, units);

        listView = (ListView) findViewById(R.id.lv_unitListView);
        listView.setAdapter(unitCustomAdapter);


        // react to
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppState.getInstance().setCurrentUnit(units.get(position));
                startUnitAddIntent();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().setCurrentUnit(null);
                startUnitAddIntent();
            }
        });
    }



    private void startUnitAddIntent() {
        Intent intentAddUnit = new Intent(getApplicationContext(), UnitAddActivity.class);
        startActivity(intentAddUnit);
    }


    /**
     * Just a small Test, deleting two records from the Units Table
     * and refreshing the listView
     *
     * seems to work :)
     *
     */
    private void testDeleteEntries() {
        db.deleteSome(); // in der Datenbank löschen
        units = db.getUnitsData(); // aktuelle Daten holen
        unitCustomAdapter.setUnits(units); // daten in unitCustomAdapter setzen
        listView.invalidateViews(); // listView neu zeichnen
    }

}
