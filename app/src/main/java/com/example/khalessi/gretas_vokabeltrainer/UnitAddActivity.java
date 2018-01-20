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

                boolean callVocListActivity = false;
                Unit currentUnit = AppState.getInstance().getCurrentUnit();

                if (currentUnit == null) { // keine aktuelle unit
                    // erzeuge neue Unit, falls Form ausgefüllt
                    callVocListActivity = insertNewUnit();
                } else {
                    // TODO verarbeite die gesetzte Unit, update der Unit mit den gesetzten Werten
                    callVocListActivity = updateCurrentUnit();
                }

                // show voc list view
                if (callVocListActivity) {
                    Intent intent_testListView = new Intent(getApplicationContext(), VocListActivity.class);
                    startActivity(intent_testListView);
                }
            }
        });

        Button btn_cancel = (Button) findViewById(R.id.btn_addEntryCancel);
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
     *
     */

    /**
     * Processes the current EditText fields
     * and generates a new database entry.
     * <p>
     * It then queries the database to get this unit (based on the generated unitId)
     * and sets it as the current unit in the AppState,
     * thus making ist public to the rest of the App.
     *
     * @return true, if new Unit has been inserted
     */
    private boolean insertNewUnit() {
        EditText et_unit_add_lektionsTitle = (EditText) findViewById(R.id.et_unit_add_lektionsTitle);
        String title = et_unit_add_lektionsTitle.getText().toString();

        EditText et_unit_add_lektionsDescription = (EditText) findViewById(R.id.et_unit_add_lektionsDescription);
        String description = et_unit_add_lektionsDescription.getText().toString();

        EditText et_unit_add_lektionsUsername = (EditText) findViewById(R.id.et_unit_add_lektionsUsername);
        String userName = et_unit_add_lektionsUsername.getText().toString();

        String unitID = UnitIdGenerator.generate();

        // Sind die Daten vollständig ?
        boolean dataComplete = checkForm(title, description, userName, unitID);

        if (dataComplete) {
            // Einfügen in Datenbank
            AppState.getInstance().getDatabaseHelper()
                    .insertUnit(new Unit(unitID, userName, title, description));

            // Hole die Unit und setze sie in AppState als current unit, hat noch keine voc list
            Unit unit = AppState.getInstance().getDatabaseHelper().getUnit(unitID, false);
            if (unit != null) {
                AppState.getInstance().setCurrentUnit(unit);
            }
        }

        return dataComplete;
    }

    private boolean updateCurrentUnit() {
        // Daten aus der Form holen
        EditText et_unit_add_lektionsTitle = (EditText) findViewById(R.id.et_unit_add_lektionsTitle);
        String title = et_unit_add_lektionsTitle.getText().toString();

        EditText et_unit_add_lektionsDescription = (EditText) findViewById(R.id.et_unit_add_lektionsDescription);
        String description = et_unit_add_lektionsDescription.getText().toString();

        EditText et_unit_add_lektionsUsername = (EditText) findViewById(R.id.et_unit_add_lektionsUsername);
        String userName = et_unit_add_lektionsUsername.getText().toString();

        boolean result = false;

        // Current Unit holen
        Unit currentUnit = AppState.getInstance().getCurrentUnit();
        return updateUnit(currentUnit, title, description, userName);
    }

    private boolean updateUnit(Unit currentUnit, String title, String description, String userName) {
        boolean result;
        if (currentUnit != null) {
            String unitID = currentUnit.getUnitId();

            result = checkForm(title, description, userName, unitID);

            // Sind die Daten vollständig ?
            if (result) {

                // Daten aus Form in currentUnit übertragen
                currentUnit.setTitle(title);
                currentUnit.setDescription(description);
                currentUnit.setUser(userName);
                currentUnit.setUnitId(unitID);

                // update returns true or false
                result = AppState.getInstance().getDatabaseHelper().updateUnit(currentUnit);

                // wenn update auf der Datenbank funktioniert hat, wird current Unit im AppState gesetzt
                if (result) {
                    AppState.getInstance().setCurrentUnit(currentUnit);
                }
            }
        } else {
            result = false;
        }

        return result;
    }


    private boolean checkForm(String title, String description, String userName, String unitID) {
        boolean dataComplete;
        dataComplete = !(
                (title.trim().equalsIgnoreCase("")) ||
                (description.trim().equalsIgnoreCase("")) ||
                (userName.trim().equalsIgnoreCase("")) ||
                (unitID.trim().equalsIgnoreCase(""))
        );
        return dataComplete;
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
            // es muss eine neue Unit erstellt werden, also die Felder sind leer bis auf UserName

            // die eigentliche Erstellung der Unit passiert in der Callbackroutine beim Speichern der Daten

            // aktuellen Usernamen anzeigen
            EditText et_unit_add_lektionsUsername = (EditText) findViewById(R.id.et_unit_add_lektionsUsername);
            et_unit_add_lektionsUsername.setText(AppState.getInstance().getCurrentUserName());
        }

    }
}
