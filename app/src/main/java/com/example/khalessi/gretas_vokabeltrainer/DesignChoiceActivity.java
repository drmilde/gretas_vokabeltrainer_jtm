package com.example.khalessi.gretas_vokabeltrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.khalessi.gretas_vokabeltrainer.database.DatabaseHelper;
import com.example.khalessi.gretas_vokabeltrainer.state.AppState;

public class DesignChoiceActivity extends AppCompatActivity {
    private Button btn_katzenDesign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_choice);

        // setup the database
        AppState.getInstance().setDatabaseHelper(new DatabaseHelper(this));

        btn_katzenDesign = (Button) findViewById(R.id.btn_katzenDesign);
        btn_katzenDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // holt Name aus dem EditText Field
                EditText et_benutzerName = (EditText) findViewById(R.id.et_benutzerName);
                String currentUserName = et_benutzerName.getText().toString();
                AppState.getInstance().setCurrentUserName(currentUserName);


                Intent unitIntent = new Intent(getApplicationContext(), UnitActivity.class);
                startActivity(unitIntent);
            }
        });

        // TODO kann später weg, nur zu Testzwecken
        Button btn_testListView = (Button) findViewById(R.id.btn_testListView);
        btn_testListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_testListView = new Intent(getApplicationContext(), VocListActivity.class);
                startActivity(intent_testListView);

            }
        });


        // TODO Datenbank für Vokabeln anlegen (Kopie von Unit und anpassen)
        // TODO setzten dynamisch Hintergrundbild per Java
        // TODO Gestalterisch: app theme entwickeln
        // TODO Vokabeln übersetzen
        // TODO Styles entwickeln
        // TODO Dokumentationskommentare einfügen
        // TODO Impressum Activity entwickeln
        // TODO Eintragen von Vokabeln in die Datenbank
        // TODO Entragen von Unit in die Datenbank
        // TODO Angabe des Benutzernamens
        // TODO Systemweite Speicherung der aktuellen Daten in einem Singleton
        // TODO Übungsmodi generieren
        // TODO Statistiken programmieren (weit weg)
        // TODO ListView clickbar machen -> Unit auswählen und dann Vokabeln hinzufügen können
        // TODO Bilder und Übersetzungen anfertigen
        // TODO Entwicklungsnotizen starten (in Google Docs) !!!


    }
}
