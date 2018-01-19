package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.helper.UnitIdGenerator;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

public class UnitEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_unit);

        // aktuell ausgewählte unit anzeigen und bearbeiten
        showCurrentUnitData();

        // Clicks verarbeiten
        Button btn_speichern = (Button) findViewById(R.id.btn_editEntrySubmit);
        btn_speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Unit currentUnit = AppState.getInstance().getCurrentUnit();
                if (currentUnit == null) { // keine aktuelle unit
                    // erzeuge neue Unit
                    insertNewUnit();
                } else {
                    // TODO verarbeite die gesetzte Unit, keine Ahnung was hier passieren soll
                    // TODO hier muss ein update auf die currentUnit erfolgen
                }

                // show voc list view
                Intent intent_testListView = new Intent(getApplicationContext(), VocListActivity.class);
                startActivity(intent_testListView);

            }
        });

        Button btn_cancel = (Button) findViewById(R.id.btn_editEntryCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO: Add a new unit to the table", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        showCurrentUnitData(); // zeige die aktuellen Daten an
    }

    /**
     * Processes the current EditText fields
     * and generates a new database entry.
     * <p>
     * It then queries the database to get this unit (based on the generated unitId)
     * and sets it as the current unit in the AppState,
     * thus making ist public to the rest of the App.
     */
    private void insertNewUnit() {
        EditText et_unit_add_lektionsTitle = (EditText) findViewById(R.id.et_unit_add_lektionsTitle);
        String title = et_unit_add_lektionsTitle.getText().toString();

        EditText et_unit_add_lektionsDescription = (EditText) findViewById(R.id.et_unit_add_lektionsDescription);
        String description = et_unit_add_lektionsDescription.getText().toString();

        EditText et_unit_add_lektionsUsername = (EditText) findViewById(R.id.et_unit_add_lektionsUsername);
        String userName = et_unit_add_lektionsUsername.getText().toString();

        String unitId = UnitIdGenerator.generate();

        // Einfügen in Datenbank
        AppState.getInstance().getDatabaseHelper()
                .insertUnit(new Unit(unitId, userName, title, description));

        // Hole die Unit und setze sie in AppState als current unit, hat noch keine voc list
        Unit unit = AppState.getInstance().getDatabaseHelper().getUnit(unitId, false);
        if (unit != null) {
            AppState.getInstance().setCurrentUnit(unit);
        }
    }

    /**
     * Displays the current unit data.
     * <p>
     * If no current unit is set, then the global user name will be used
     * and default values will be displayed.
     */
    private void showCurrentUnitData() {
        if (AppState.getInstance().getCurrentUnit() != null) {
            // bestehende unit bearbeiten

            // title anzeigen
            EditText et_unit_add_lektionsTitle = (EditText) findViewById(R.id.et_unit_add_lektionsTitle);
            et_unit_add_lektionsTitle.setText(AppState.getInstance().getCurrentUnit().getTitle());

            // description anzeigen
            EditText et_unit_add_lektionsDescription = (EditText) findViewById(R.id.et_unit_add_lektionsDescription);
            et_unit_add_lektionsDescription.setText(AppState.getInstance().getCurrentUnit().getDescription());

            // in der unit gespeicherten username anzeigen
            EditText et_unit_add_lektionsUsername = (EditText) findViewById(R.id.et_unit_add_lektionsUsername);
            et_unit_add_lektionsUsername.setText(AppState.getInstance().getCurrentUnit().getUser());

        } else {
            // neue Unit erstellen

            // aktuellen Usernamen anzeigen
            EditText et_unit_add_lektionsUsername = (EditText) findViewById(R.id.et_unit_add_lektionsUsername);
            et_unit_add_lektionsUsername.setText(AppState.getInstance().getCurrentUserName());
        }
    }

}
