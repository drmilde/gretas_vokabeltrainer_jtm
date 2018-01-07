package com.example.khalessi.gretas_vokabeltrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.UnitDatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.ArrayList;

public class UnitActivity extends AppCompatActivity {

    private UnitCustomAdapter unitCustomAdapter = null;
    private ListView listView = null;
    private UnitDatabaseHelper db = null;
    private ArrayList<Unit> units = null;


    // Dialog

    private AlertDialog deleteDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup the database
        db = new UnitDatabaseHelper(this);
        db.recreateDatabase();
        db.insertSomeUnits();
        units = db.getUnitsData();
        unitCustomAdapter = new UnitCustomAdapter(this, R.layout.unit_details, units);

        listView = (ListView) findViewById(R.id.lv_unitListView);
        listView.setAdapter(unitCustomAdapter);


        // react to click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // save current unit and start UnitAddActivity
                AppState.getInstance().setCurrentUnit(units.get(position));
                startUnitAddIntent();
            }
        });

        // react to long click for deletion
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createDeleteDialog(position);
                deleteDialog.show();
                return true;
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

    private void createDeleteDialog(final int position) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(getString(R.string.unit_delete_title_text) + "\n\n" + units.get(position).getTitle());
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                R.string.unit_delete_dialog_yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteListItem(position);
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                R.string.unit_delete_dialog_no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        deleteDialog = builder1.create();
    }


    private void deleteListItem(int position) {
        int id = units.get(position).get_id();
        db.deleteUnit(id);
        updateListView();
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
        updateListView();
    }

    private void updateListView() {
        units = db.getUnitsData(); // aktuelle Daten holen
        unitCustomAdapter.setUnits(units); // daten in unitCustomAdapter setzen
        listView.invalidateViews(); // listView neu zeichnen
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }
}
