package com.example.khalessi.gretas_vokabeltrainer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.khalessi.gretas_vokabeltrainer.database.Unit;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

public class EntryAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // this
        showCurrentUnitTitle();

        // processing clicks

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add a new vocab to the table", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // TODO reset EditText fields to standard values
            }
        });

        //
        Button btn_addEntry = (Button) findViewById(R.id.btn_addEntrySubmit);
        btn_addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Eintrag in Datenbank einfügen, bzw. aktualisieren
            }
        });

        Button btn_cancelAddEntry = (Button) findViewById(R.id.btn_addEntryCancel);
        btn_cancelAddEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showCurrentUnitTitle() {
        Unit currentUnit = AppState.getInstance().getCurrentUnit();
        if (currentUnit != null) {

            // get the title of the current unit and set it in the EntryAddActivity
            EditText et_entry_add_lektionstitel = (EditText)findViewById(R.id.et_entry_add_lektionstitel);
            et_entry_add_lektionstitel.setText(AppState.getInstance().getCurrentUnit().getTitle());

        }
    }

}
