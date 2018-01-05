package com.example.khalessi.gretas_vokabeltrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.UnitDatabaseHelper;

import java.util.ArrayList;

public class UnitListActivity extends AppCompatActivity {

    // TODO diese klasse kann später weg, war nur zu Testzwecken drin

    UnitCustomAdapter unitCustomAdapter = null;
    ListView listView = null;
    UnitDatabaseHelper db = null;
    ArrayList<Unit> units = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_list);

        db = new UnitDatabaseHelper(this);
        db.recreateDatabase();
        db.insertSomeUnits();
        units = db.getUnitsData();
        unitCustomAdapter = new UnitCustomAdapter(this, R.layout.unit_details, units);

        listView = (ListView) findViewById(R.id.simpleListView);
        listView.setAdapter(unitCustomAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(units.get(position).getDescription());
            }
        });

    }

    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
