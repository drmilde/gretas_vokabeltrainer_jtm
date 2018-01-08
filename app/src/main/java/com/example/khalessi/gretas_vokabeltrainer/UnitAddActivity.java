package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.database.UnitDatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.database.UnitIdGenerator;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

public class UnitAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_unit);

        // aktuell ausgewählte unit anzeigen und bearbeiten
        showCurrentUnitData();

        // Clicks verarbeiten
        Button btn_speichern = (Button) findViewById(R.id.btn_addEntrySubmit);
        btn_speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppState.getInstance().getCurrentUnit() == null) { // keine aktuelle unit
                    insertNewUnit();
                } else {
                    // TODO verarbeite die gesetzte Unit
                }

                Intent intent_addEntry = new Intent(getApplicationContext(), EntryAddActivity.class);
                startActivity(intent_addEntry);
            }
        });

        Button btn_cancel = (Button) findViewById(R.id.btn_addEntryCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
     *
     * It then queries the database to get this unit (based on the generated unitId)
     * and sets it as the current unit in the AppState,
     * thus making ist public to the rest of the App.
     *
     */
    private void insertNewUnit() {
        EditText et_unit_add_lektionsTitle = (EditText)findViewById(R.id.et_unit_add_lektionsTitle);
        String title = et_unit_add_lektionsTitle.getText().toString();

        EditText et_unit_add_lektionsDescription = (EditText)findViewById(R.id.et_unit_add_lektionsDescription);
        String description = et_unit_add_lektionsDescription.getText().toString();

        EditText et_unit_add_lektionsUsername = (EditText)findViewById(R.id.et_unit_add_lektionsUsername);
        String userName = et_unit_add_lektionsUsername.getText().toString();

        String unitID = UnitIdGenerator.generate();

        // Einfügen in Datenbank
        AppState.getInstance().getDatabaseHelper()
                .insertUnit(unitID, userName, title, description);

        // Hole die Unit und setze sie in AppState als current unit
        Unit unit = AppState.getInstance().getDatabaseHelper().getUnit(unitID);
        if (unit != null) {
            AppState.getInstance().setCurrentUnit(unit);
        }
    }

    /**
     * Displays the current unit data.
     *
     * If no current unit is set, then the global user name will be used
     * and default values will be displayed.
     *
     */
    private void showCurrentUnitData() {
        if (AppState.getInstance().getCurrentUnit() != null){
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
