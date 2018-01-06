package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                //TODO neuen Eintrag in der Unit-Datenbank erzeugen
                //TODO Intent muss LektionsID erhalten
                // TODO oder bestehende Unit updaten
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
