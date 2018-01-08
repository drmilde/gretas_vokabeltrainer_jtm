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

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

import java.util.ArrayList;

public class UnitActivity extends AppCompatActivity {

    private UnitCustomAdapter unitCustomAdapter = null;
    private ListView listView = null;
    private DatabaseHelper db = null;
    private ArrayList<Unit> units = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // setup the database
        db = AppState.getInstance().getDatabaseHelper();
        db.recreateDatabase();
        db.insertSomeUnits();
        units = db.getUnitsData();
        unitCustomAdapter = new UnitCustomAdapter(this, R.layout.unit_details, units);

        // connect listView and Adapter
        listView = (ListView) findViewById(R.id.lv_unitListView);
        listView.setAdapter(unitCustomAdapter);


        // react to click
        // setting the current unit to the selected one
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
                createDeleteDialog(position).show();
                return true;
            }
        });


        // use floating button to add a new unit
        // this is indicated by setting the current unit to null
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppState.getInstance().setCurrentUnit(null);
                startUnitAddIntent();
            }
        });
    }


    /**
     *
     * Creates an AlertDialog with registered handlers for the deletion
     * at the position of the ListView.
     *
     * @param position position in the ListView
     * @return an AlertDialog with registered handlers
     */
    private AlertDialog createDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.unit_delete_title_text) + "\n\n" + units.get(position).getTitle());
        builder.setCancelable(true);

        builder.setPositiveButton(
                R.string.unit_delete_dialog_yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteListItem(position);
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton(
                R.string.unit_delete_dialog_no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return (builder.create());
    }

    /**
     *
     * Deletes the unit at position in the ArrayList from the database
     * and updates the ListView.
     *
     * @param position of unit in ArrayListe
     */
    private void deleteListItem(int position) {
        int id = units.get(position).get_id();
        db.deleteUnit(id);
        updateListView();
    }

    /**
     * Starts the UnitAddActivity.
     */
    private void startUnitAddIntent() {
        Intent intentAddUnit = new Intent(getApplicationContext(), UnitAddActivity.class);
        startActivity(intentAddUnit);
    }


    /**
     * Updates the content of the ListView.
     */
    private void updateListView() {
        units = db.getUnitsData(); // aktuelle Daten holen
        unitCustomAdapter.setUnits(units); // daten in unitCustomAdapter setzen
        listView.invalidateViews(); // listView neu zeichnen
    }


    @Override
    protected void onResume() {
        super.onResume();
        // if database has changed, we need to update the ListView
        updateListView();
    }

    // **********************************************************
    // ****************************** TEST **********************
    // **********************************************************

    /**
     *
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

}
